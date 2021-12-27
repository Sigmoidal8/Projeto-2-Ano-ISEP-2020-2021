package presentation;

import ch.qos.logback.core.net.ssl.SSLConfigurableSocket;
import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.repositories.CatalogRepository;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.requestmanagement.domain.RequestDate;
import eapli.base.requestmanagement.domain.RequestStatus;
import eapli.base.requestmanagement.repositories.RequestRepository;
import eapli.base.taskmanagement.domain.*;
import eapli.base.taskmanagement.repositories.TaskExecutionRepository;
import eapli.base.teammanagement.domain.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.embedded.netty.SslServerCustomizer;
import protocol.RequestingProtocolMessageParser;
import protocol.RequestingProtocolRequest;

import javax.net.ssl.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author Raúl Coelho
 */
public class WorkflowServer {

    private static final Logger LOGGER = LogManager.getLogger(WorkflowServer.class);
    private static final List<String> SERVER_IP_ADDRESS = new ArrayList<>();
    private static int LAST_SERVER_ASSIGNED = 0;
    private static final int WORKFLOW_PORT = 32507;
    private static final byte VERSION = 0;
    private static final String TRUSTED_STORE = "SSL/server_J.jks";
    private static final String KEYSTORE_PASS = "forgotten";
    private static SSLServerSocketFactory sslf = null;
    private static Semaphore sem = new Semaphore(1);
    private static final CatalogRepository catalogRepository = PersistenceContext.repositories().catalogs();
    private static final TaskExecutionRepository taskExecutionRepository = PersistenceContext.repositories().taskExecutions();

    public WorkflowServer(List<String> taskExecutors) {
        for (String s : taskExecutors) {
            SERVER_IP_ADDRESS.add(s);
        }
        new WorkflowManager().start();
        start(WORKFLOW_PORT, true);
    }

    private static class WorkflowManager extends Thread {

        private final RequestRepository requestRepository = PersistenceContext.repositories().requests();

        @Override
        public void run() {
            System.out.println("Starting...");
            while (true) {
                System.out.println("Finding Services...");
                Iterable<Request> requests = requestRepository.findIncompletedRequests();
                for (Request r : requests) {
                    int i = 0;
                    if (r.status().compareTo(RequestStatus.Submited) == 0) {
                        if (!r.taskExecutions().get(0).task().getClass().equals(ApprovalTask.class)) {
                            if (!r.taskExecutions().get(0).task().getClass().equals(ManualTask.class)) {
                                r.taskExecutions().get(0).changeStatus(TaskStatus.active);
                                Metrics metrics = new Metrics(new Date());
                                r.taskExecutions().get(0).inputMetrics(metrics);
                                callTaskExecutor(r, r.taskExecutions().get(0), requestRepository);
                            } else {
                                r.changeStatus(RequestStatus.Resolving);
                                r.taskExecutions().get(0).changeStatus(TaskStatus.active);
                                Metrics metrics = new Metrics(new Date());
                                r.taskExecutions().get(0).inputMetrics(metrics);
                                callTaskExecutorAlgoritms(r.taskExecutions().get(0));
                            }
                        } else {
                            r.changeStatus(RequestStatus.InAproval);
                            r.taskExecutions().get(0).changeStatus(TaskStatus.active);
                            Metrics metrics = new Metrics(new Date());
                            r.taskExecutions().get(0).inputMetrics(metrics);
                        }
                    }
                    if (r.status().compareTo(RequestStatus.InAproval) == 0) {

                    }
                    if (r.status().compareTo(RequestStatus.Aproved) == 0) {
                        r.taskExecutions().get(1).changeStatus(TaskStatus.active);
                        Metrics metrics = new Metrics(new Date());
                        r.taskExecutions().get(1).inputMetrics(metrics);
                        if (r.taskExecutions().get(1).task().getClass().equals(AutomaticTask.class)) {
                            callTaskExecutor(r, r.taskExecutions().get(1), requestRepository);
                        } else {
                            r.changeStatus(RequestStatus.Resolving);
                            callTaskExecutorAlgoritms(r.taskExecutions().get(1));
                        }
                    }
                    if (r.status().compareTo(RequestStatus.Resolving) == 0) {
                        if (r.taskExecutions().get(0).task().getClass().equals(ApprovalTask.class)) {
                            if (r.taskExecutions().get(1).status().compareTo(TaskStatus.completed) == 0) {
                                r.changeStatus(RequestStatus.Concluded);
                                r.changeConclusionDate(new RequestDate(new Date()));
                            }
                        } else {
                            if (r.taskExecutions().get(0).status().compareTo(TaskStatus.completed) == 0) {
                                r.changeStatus(RequestStatus.Concluded);
                                r.changeConclusionDate(new RequestDate(new Date()));
                            }
                        }
                    }
                    try {
                        sem.acquire();
                        requestRepository.save(r);
                        sem.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }
            }
        }
    }

    private static void callTaskExecutorAlgoritms(TaskExecution task) {
        System.out.println("Search Collaborator to pendent task");
        switch (System.getProperty("assigntasksautomatic")) {
            case "FCFS":
                assignTasksFCFS(task);
                break;
            case "Load":
                assignTasksByLoad(task);
                break;
        }
    }

    private static void assignTasksByLoad(TaskExecution tasksWithoutColab) {
        long startTime = System.nanoTime();
        Collaborator assignCollaborator = null;
        int occupatedTime = Integer.MAX_VALUE;
        Request request = taskExecutionRepository.findRequestTasks(tasksWithoutColab);
        Catalog catalog = catalogRepository.findByService(request.service());
        List<Team> catalogTeams = catalog.criteria().teams();
        for (Team team : catalogTeams) {
            for (Collaborator c : team.collaborators()) {
                int cont = 0;
                List<TaskExecution> collaboratorTasks = (List<TaskExecution>) taskExecutionRepository.findColaboratorPendingTasks(c);
                for (TaskExecution t : collaboratorTasks) {
                    Request request2 = taskExecutionRepository.findRequestTasks(t);
                    cont += request2.service().criticality().objectives().tempoMedResolucao().toSecondOfDay();
                }
                if (cont < occupatedTime) {
                    occupatedTime = cont;
                    assignCollaborator = c;
                }
            }
        }
        Date d = new Date();
        tasksWithoutColab.changeClaimTaskDate(d);
        tasksWithoutColab.changeResponsibleCollaborator(assignCollaborator);
        long endTime = System.nanoTime();

        taskExecutionRepository.save(tasksWithoutColab);
        System.out.println("Task assigned to Collaborator: " + assignCollaborator.toString());
        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }

    private static void assignTasksFCFS(TaskExecution tasksWithoutColab) {
        long startTime = System.nanoTime();
        Collaborator assignCollaborator = null;
        int pendingTaskOfDay = Integer.MAX_VALUE;
        Date d = new Date();
        Date currentDate = new Date();
        Request request = taskExecutionRepository.findRequestTasks(tasksWithoutColab);
        Catalog catalog = catalogRepository.findByService(request.service());
        List<Team> catalogTeams = catalog.criteria().teams();
        TeamLoop:
        for (Team team : catalogTeams) {
            for (Collaborator c : team.collaborators()) {
                List<TaskExecution> collaboratorTasks = (List<TaskExecution>) taskExecutionRepository.findAllColaboratorTasks(c);
                int pendingTasksCollaborator = collaboratorTasks.size();
                if (collaboratorTasks.isEmpty()) {
                    assignCollaborator = c;
                    pendingTaskOfDay = 1;
                    break TeamLoop;
                } else {
                    Date d2 = collaboratorTasks.get(0).claimTaskDate();
                    TaskExecution task = collaboratorTasks.get(0);
                    for (TaskExecution t : collaboratorTasks) {
                        if (t.claimTaskDate().compareTo(d2) > 0) {
                            d2 = t.claimTaskDate();
                            task = t;
                        }
                    }
                    
                    if (task.claimTaskDate().compareTo(d) < 0) {
                        assignCollaborator = c;
                        d = task.claimTaskDate();
                        pendingTaskOfDay = pendingTasksCollaborator;
                    } else if (task.claimTaskDate().equals(d) && pendingTasksCollaborator < pendingTaskOfDay) {
                        assignCollaborator = c;
                        d = task.claimTaskDate();
                        pendingTaskOfDay = pendingTasksCollaborator;
                    }
                }
            }
        }
        currentDate = new Date();
        tasksWithoutColab.changeClaimTaskDate(currentDate);
        tasksWithoutColab.changeResponsibleCollaborator(assignCollaborator);
        long endTime = System.nanoTime();
        taskExecutionRepository.save(tasksWithoutColab);
        System.out.println("Task assigned to Collaborator: " + assignCollaborator.toString());
        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in nanoseconds FCFS: " + timeElapsed);
        System.out.println("Execution time in milliseconds FCFS: " + timeElapsed / 1000000);

    }

    private static void callTaskExecutor(Request request, TaskExecution task, RequestRepository requestRepository) {
        switch (System.getProperty("algorithm")) {
            case "FCFS":
                if (LAST_SERVER_ASSIGNED == SERVER_IP_ADDRESS.size() - 1) {
                    LAST_SERVER_ASSIGNED = 0;
                } else {
                    LAST_SERVER_ASSIGNED++;
                }

                new CallAutomaticTaskExecutor(request, SERVER_IP_ADDRESS.get(LAST_SERVER_ASSIGNED), task, requestRepository).start();
                break;
            case "Other":
                String server = null;
                int lessTasks = Integer.MAX_VALUE;
                for (String s : SERVER_IP_ADDRESS) {
                    int lessValues = checkTaskExecutorForTasks(s);
                    if (lessValues < lessTasks) {
                        server = s;
                        lessTasks = lessValues;
                    }
                }

                new CallAutomaticTaskExecutor(request, server, task, requestRepository).start();
        }
    }

    private static int checkTaskExecutorForTasks(String serverIP) {
        final int SERVER_PORT = 32507;
        int numberTasks = 0;
        InetAddress server = null;
        SSLSocket socket = null;

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            server = InetAddress.getByName(serverIP);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server: " + serverIP);
            System.exit(1);
        }

        try {
            socket = (SSLSocket) sf.createSocket(server, SERVER_PORT);
        } catch (IOException ex) {
            System.out.println("Failed to connect.");
            System.exit(1);
        }

        try {
            socket.startHandshake();

            DataOutputStream sOut = new DataOutputStream(socket.getOutputStream());
            DataInputStream sIn = new DataInputStream(socket.getInputStream());
            System.out.println("Connected to server " + serverIP);

            System.out.println("Asking for Tasks of server");
            byte[] message = {(byte) VERSION, (byte) 20, (byte) 0, (byte) 0};
            sOut.write(message);
            sOut.flush();

            String threads = sIn.readUTF();
            numberTasks = Integer.parseInt(threads) - 1;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Closing connection...");
                socket.close();
                System.out.println("Connection closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return numberTasks;
    }

    private static class CallAutomaticTaskExecutor extends Thread {

        private String SERVER_IP = null;
        private static final int SERVER_PORT = 32507;
        private InetAddress serverIP;
        private SSLSocket socket;
        private Request request;
        private TaskExecution task;
        private RequestRepository requestRepository;

        public CallAutomaticTaskExecutor(final Request request, final String server, final TaskExecution task, final RequestRepository requestRepository) {
            this.request = request;
            this.SERVER_IP = server;
            this.task = task;
            this.requestRepository = requestRepository;
        }

        @Override
        public void run() {
            SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

            try {
                serverIP = InetAddress.getByName(SERVER_IP);
            } catch (UnknownHostException ex) {
                System.out.println("Invalid server: " + SERVER_IP);
                System.exit(1);
            }

            try {
                socket = (SSLSocket) sf.createSocket(serverIP, SERVER_PORT);
            } catch (IOException ex) {
                System.out.println("Failed to connect.");
                System.exit(1);
            }

            try {
                socket.startHandshake();

                DataOutputStream sOut = new DataOutputStream(socket.getOutputStream());
                DataInputStream sIn = new DataInputStream(socket.getInputStream());
                System.out.println("Connected to server " + SERVER_IP);

                System.out.println("Passing task to Task Executor");

                byte[] message = {(byte) VERSION, (byte) 21, (byte) 0, (byte) 0};
                sOut.write(message);
                sOut.flush();

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(request);
                out.flush();

                String output = sIn.readUTF();
                System.out.println(output);

                sem.acquire();
                request = requestRepository.ofIdentity(request.identity()).get();
                for (TaskExecution t : request.taskExecutions()) {
                    if (t.identity() == task.identity()) {
                        task = t;
                        break;
                    }
                }

                task.changeStatus(TaskStatus.completed);
                request.changeStatus(RequestStatus.Concluded);

                Date currentDate = new Date();
                Date startDate = task.metrics().resolutionTime();

                long diffInMillies = Math.abs(currentDate.getTime() - startDate.getTime());

                Date date = new Date(diffInMillies);
                Metrics metrics = new Metrics(date);

                task.inputMetrics(metrics);
                request.changeConclusionDate(new RequestDate(new Date()));

                requestRepository.save(request);
                sem.release();

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    System.out.println("Closing connection...");
                    socket.close();
                    System.out.println("Connection closed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class RequestHandler extends Thread {

        private Socket requestSocket;

        public RequestHandler(final Socket socket) {
            this.requestSocket = socket;
        }

        @Override
        public void run() {
            final InetAddress requestIP = requestSocket.getInetAddress();
            System.out.println("New client connection from " + requestIP.getHostAddress()
                    + ", port number " + requestSocket.getPort());
            LOGGER.debug("Acepted connection from {}:{}", requestIP.getHostAddress(), requestSocket.getPort());
            try ( DataOutputStream out = new DataOutputStream(requestSocket.getOutputStream());  DataInputStream in = new DataInputStream(requestSocket.getInputStream())) {

                int version = in.readByte();
                int code = in.readByte();
                final RequestingProtocolRequest request = RequestingProtocolMessageParser.parse(code, in);
                final String response = request.execute();
                out.writeUTF(response);
                out.flush();
                //sendMessage(response, 6, out);
//                if (responseString.length > 255) {
//                    byte[] aux = new byte[255];
//                    while(responseString.length - i > 255){
//                        for(int l = 0 ; l < 255; l++){
//                            aux[l] = responseString[l+(255*vez)];
//                            i++;
//                        }
//                        System.out.println(aux);
//                        sendMessage(aux, 255, out);
//                        vez++;
//                    }
//                    aux = new byte[255];
//                    for(int l = 0; l <responseString.length-i; l++){
//                        aux[l] = responseString[l+(255*vez)];
//                    }
//                    System.out.println(aux);
//
//                    sendMessage(aux,6, out);
//                } else {
//                    System.out.println(response);
//                    sendMessage(response, 6, out);
//                }
            } catch (final IOException e) {
                e.printStackTrace();
                LOGGER.error(e);
            } finally {
                try {
                    requestSocket.close();
                } catch (final IOException e) {
                    LOGGER.error("While closing the client socket", e);
                }
            }
        }
    }

    private void listen(final int port) {
        // Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        sslf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        SSLServerSocket serverSocket = null;
        try {
            serverSocket = (SSLServerSocket) sslf.createServerSocket(port);
            serverSocket.setNeedClientAuth(true);

            while (true) {
                Socket cliSocket = serverSocket.accept();
                new RequestHandler(cliSocket).start();
            }
        } catch (final IOException e) {
            System.out.println("Error");
            LOGGER.error(e);
        }
    }

    public void start(final int port, final boolean blocking) {
        if (blocking) {
            listen(port);
        } else {
            new Thread(() -> listen(port)).start();
        }
    }

    static void sendMessage(String content, int code, DataOutputStream sOut) throws IOException {
        byte[] data;

        data = content.getBytes();
        int nBytes = data.length;
        System.out.println(nBytes);

        sOut.writeByte((byte) VERSION);
        sOut.writeByte((byte) code);
        sOut.writeByte((byte) nBytes);
        sOut.write(data, 0, (byte) nBytes);
    }

    static void sendMessage(byte[] content, int code, DataOutputStream sOut) throws IOException {
        int nBytes = content.length;
        System.out.println(nBytes);

        sOut.writeByte(VERSION);
        sOut.writeByte((byte) code);
        sOut.writeByte((byte) nBytes);
        sOut.write(content, 0, (byte) nBytes);
    }
}
