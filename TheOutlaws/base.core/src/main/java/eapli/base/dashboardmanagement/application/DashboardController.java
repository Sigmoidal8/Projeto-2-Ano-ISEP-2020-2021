/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.dashboardmanagement.application;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.domain.MecanographicNumber;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.dashboardmanagement.domain.HttpServerAjaxVoting;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.requestmanagement.domain.Priority;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.base.taskmanagement.repositories.TaskExecutionRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author migue
 */
public class DashboardController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final HttpServerAjaxVoting server = new HttpServerAjaxVoting();
    private final TaskExecutionRepository taskExecutionRepository = PersistenceContext.repositories().taskExecutions();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();
    private Collaborator collaborator = null;
    static final String TRUSTED_STORE = "SSL/client1_J.jks";
    static final String KEYSTORE_PASS = "forgotten";

    public void showDashboard() {
        Optional<Collaborator> optional = collaboratorRepository.findByEmail(authz.session().get().authenticatedUser().email());

        if (optional.get() != null) {
            collaborator = optional.get();
        }
        server.setColab(collaborator);
        server.changeController(this);
        askWorkflowForData();
        server.start();

    }

    public void askWorkflowForData() {
        final String SERVER_IP_ADDRESS = "10.9.21.82";
        final int SERVER_PORT = 32507;
        final byte VERSION = 0;
        InetAddress serverIP = null;
        SSLSocket socket = null;
        byte[] data = new byte[300];
        String dataString = "";

        // Trust these certificates provided by servers
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        // Use this certificate and private key for client certificate when requested by the server
        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            serverIP = InetAddress.getByName(SERVER_IP_ADDRESS);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server: " + SERVER_IP_ADDRESS);
            System.exit(1);
        }

        try {
            socket = (SSLSocket) sf.createSocket(serverIP, SERVER_PORT);
        } catch (IOException ex) {
            System.out.println("Failed to connect.");
            System.exit(1);
        }

        try {
            //System.out.println("Connected to: " + SERVER_IP_ADDRESS + ":" + SERVER_PORT);
            socket.startHandshake();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            String content = collaborator.identity().toString();
            //System.out.println("Passing collborator id to dashboardExecutor");

            if (content.length() > 255) {
                String[] parts = splitN(content, content.length() / 255);
                for (int j = 0; j < parts.length - 1; j++) {
                    sendMessage(parts[j], 255, out, VERSION);
                }
                sendMessage(parts[parts.length - 1], 11, out, VERSION);
            } else {
                sendMessage(content, 11, out, VERSION);
            }

            //System.out.println("Message passed");
            dataString = in.readUTF();
        } catch (IOException e) {
            e.toString();
        } finally {
            try {
                //System.out.println("Closing connection...");
                socket.close();
                //System.out.println("Connection closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<String> information = convertToList(dataString);
        List<String> informationSortByPriority = new ArrayList<>();
        List<String> informationSortByCriticality = new ArrayList<>();
        List<String> informationSortByRequestDate = new ArrayList<>();

        if (!information.isEmpty()) {
            informationSortByPriority = sortByPriority(information);
            informationSortByCriticality = sortByCriticality(information);
            informationSortByRequestDate = sortByDeadline(information);
        }
        server.setlistOrderByCriticality(informationSortByCriticality);
        server.setlistOrderByPriority(informationSortByPriority);
        server.setlistOrderByRequestDate(informationSortByRequestDate);

    }

    public Iterable<TaskExecution> getColabTasks(String collaborator) {
        Collaborator collab = collaboratorRepository.ofIdentity(MecanographicNumber.valueOf(Long.parseLong(collaborator))).get();
        if (collab == null) {
            return null;
        } else {
            return taskExecutionRepository.findColaboratorTasks(collab);
        }
    }

    private List<String> convertToList(String tasksString) {
        List<String> information = new ArrayList<String>();
        if (tasksString != null) {
            String taskElements[] = tasksString.split(";");
            int i = 1;
            while (i < taskElements.length) {
                information.add(taskElements[i].toString());
                i++;
            }
        }
        return information;
    }

    private List<String> sortByCriticality(List<String> tasksString) {
        List<String> information = tasksString;

        for (int i = 1; i < tasksString.size(); i++) {
            String taskElements[] = tasksString.get(i).split(",");
            int criticality1 = Integer.parseInt(taskElements[5].substring(1, taskElements[5].length() - 1));
            for (int j = 0; j < i; j++) {
                String taskElements2[] = tasksString.get(j).split(",");
                int criticality2 = Integer.parseInt(taskElements2[5].substring(1, taskElements2[5].length() - 1));
                if (criticality1 > criticality2) {
                    String temp = information.get(i);
                    information.add(i, information.get(j));
                    information.add(j, temp);
                }
            }
        }

        return information;
    }

    private List<String> sortByDeadline(List<String> tasksString) {
        List<String> information = tasksString;
        for (int i = 1; i < tasksString.size(); i++) {
            for (int j = 0; j < i; j++) {
                String taskElements[] = tasksString.get(i).split(",");
                String taskElements2[] = tasksString.get(j).split(",");
                String requestDate[] = taskElements[7].substring(1, taskElements[7].length() - 1).split(" ");
                String requestDate2[] = taskElements2[7].substring(1, taskElements2[7].length() - 1).split(" ");

                if (requestDate[2].compareTo(requestDate2[2]) == 0) { // caso sejam os 2 delayed ou remainig
                    if (requestDate[2].compareTo("Delayed") == 0) {
                        if (Integer.parseInt(requestDate[0]) > Integer.parseInt(requestDate2[0])) {
                            String temp = tasksString.get(i);
                            tasksString.remove(i);
                            tasksString.add(i, tasksString.get(j));
                            tasksString.remove(j);
                            tasksString.add(j, temp);
                        }
                    } else {
                        if (Integer.parseInt(requestDate[0]) < Integer.parseInt(requestDate2[0])) {
                            String temp = tasksString.get(i);
                            tasksString.remove(i);
                            tasksString.add(i, tasksString.get(j));
                            tasksString.remove(j);
                            tasksString.add(j, temp);
                        }
                    }

                } else if ((requestDate[2].compareTo("Delayed") == 0) && ((requestDate2[2].compareTo("Remaining") == 0))) {
                    String temp = tasksString.get(i);
                    tasksString.remove(i);
                    tasksString.add(i, tasksString.get(j));
                    tasksString.remove(j);
                    tasksString.add(j, temp);
                }
            }
        }
        return information;
    }

    private List<String> sortByPriority(List<String> tasksString) {
        List<String> information = new ArrayList<String>();

        for (String t : tasksString) {
            String taskElements[] = t.split(",");
            if (taskElements[4].toString().substring(1, taskElements[4].length() - 1).equals(Priority.Urgent.toString())) {
                information.add(t);
            }
        }
        for (String t : tasksString) {
            String taskElements[] = t.split(",");
            if (taskElements[4].toString().substring(1, taskElements[4].length() - 1).equals(Priority.Moderate.toString())) {
                information.add(t);
            }
        }
        for (String t : tasksString) {
            String taskElements[] = t.split(",");
            if (taskElements[4].toString().substring(1, taskElements[4].length() - 1).equals(Priority.Reduced.toString())) {
                information.add(t);
            }
        }
        return information;
    }

    static String[] splitN(String s, final int N) {
        final int base = s.length() / N;
        final int remainder = s.length() % N;

        String[] parts = new String[N];
        for (int i = 0; i < N; i++) {
            int length = base + (i < remainder ? 1 : 0);
            parts[i] = s.substring(0, length);
            s = s.substring(length);
        }
        return parts;
    }

    private void sendMessage(String content, int code, DataOutputStream sOut, int version) throws IOException {
        byte[] data = new byte[300];

        data = content.getBytes();
        int nBytes = content.length();

        sOut.writeByte((byte) version);
        sOut.writeByte((byte) code);
        sOut.writeByte((byte) nBytes);
        sOut.write(data, 0, (byte) nBytes);
    }

}
