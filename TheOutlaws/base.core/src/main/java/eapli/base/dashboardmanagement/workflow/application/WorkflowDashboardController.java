package eapli.base.dashboardmanagement.workflow.application;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.dashboardmanagement.workflow.domain.HttpServerAjaxWorkflow;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Raúl Coelho
 */
public class WorkflowDashboardController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final HttpServerAjaxWorkflow server = new HttpServerAjaxWorkflow();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();
    private Collaborator collaborator = null;
    private final String SERVER_IP_ADDRESS = "10.9.21.82";
    private final int SERVER_PORT = 32507;
    private final byte VERSION = 0;
    static final String TRUSTED_STORE="SSL/client2_J.jks";
    static final String KEYSTORE_PASS="forgotten";

    public void showDashboard() {
        Optional<Collaborator> optional = collaboratorRepository.findByEmail(authz.session().get().authenticatedUser().email());

        if (optional.get() != null) {
            collaborator = optional.get();
        }
        server.changeCollaborator(collaborator);
        server.changeController(this);
        askWorkflowForData();
        server.start();
    }

    public void askWorkflowForData(){
        InetAddress serverIP = null;
        SSLSocket socket = null;
        byte[] data = new byte[300];
        String dataString = "";

        // Trust these certificates provided by servers
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

        // Use this certificate and private key for client certificate when requested by the server
        System.setProperty("javax.net.ssl.keyStore",TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            serverIP = InetAddress.getByName(SERVER_IP_ADDRESS);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server: " + SERVER_IP_ADDRESS);
            System.exit(1);
        }
        try {
            socket = (SSLSocket) sf.createSocket(serverIP,SERVER_PORT);
        } catch (IOException ex) {
            System.out.println("Failed to connect.");
            System.exit(1);
        }

        try {
            socket.startHandshake();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            sendMessage("", 10, out, VERSION);
            dataString = in.readUTF();
            in.close();

        } catch (IOException e) {
            e.toString();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<String> requests = convertToList(dataString);
        server.changeRequests(requests);
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
