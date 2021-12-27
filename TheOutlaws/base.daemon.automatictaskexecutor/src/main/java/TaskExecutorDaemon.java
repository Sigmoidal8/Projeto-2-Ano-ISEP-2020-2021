import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import protocol.TaskExecutorProtocolParser;
import protocol.TaskExecutorProtocolRequest;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;

/**
 * @author Raúl Coelho
 */
public class TaskExecutorDaemon {
    private static final Logger LOGGER = LogManager.getLogger(TaskExecutorDaemon.class);
    private static final int PORT = 32507;
    static final String TRUSTED_STORE = "SSL/server_J.jks";
    static final String KEYSTORE_PASS = "forgotten";

    private static ServerSocket sock;

    public static void main(String args[]) throws Exception {
        SSLServerSocket sock = null;
        Socket cliSock;


        // Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        int i;

        try {
            sock = (SSLServerSocket) sslF.createServerSocket(PORT);
            sock.setNeedClientAuth(true);
        } catch (IOException ex) {
            System.out.println("Local port number not available.");
            System.exit(1);
        }

        while (true) {
            cliSock = sock.accept(); // wait for a new client connection request
            new RequestHandler(cliSock).start();
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
            try (DataOutputStream out = new DataOutputStream(requestSocket.getOutputStream()); DataInputStream in = new DataInputStream(requestSocket.getInputStream())) {
                byte[] message = in.readNBytes(4);
                final TaskExecutorProtocolRequest request = TaskExecutorProtocolParser.parse(message[1], in);
                final String response = request.execute();
                out.writeUTF(response);
                out.flush();
            } catch (final IOException | ClassNotFoundException e) {
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
}
