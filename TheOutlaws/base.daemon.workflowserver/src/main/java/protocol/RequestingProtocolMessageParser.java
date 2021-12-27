package protocol;

import eapli.base.dashboardmanagement.application.DashboardController;
import eapli.base.dashboardmanagement.workflow.application.WorkflowDashboardController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Raúl Coelho
 */
public class RequestingProtocolMessageParser {

    private static final Logger LOGGER = LogManager.getLogger(RequestingProtocolMessageParser.class);

    private static DashboardController controller;
    private static WorkflowDashboardController theController;

    private RequestingProtocolMessageParser() {
        // avoid instantiation
    }

    /**
     * To inject a different controller for testing purposes.
     *
     * @param controller
     */

    /* package */
    public static void injectController(final DashboardController controller) {
        synchronized (lock) {
            RequestingProtocolMessageParser.controller = controller;
        }
    }

    public static void injectController(final WorkflowDashboardController controller) {
        synchronized (lock) {
            RequestingProtocolMessageParser.theController = controller;
        }
    }

    private static final Object lock = new Object();

    private static DashboardController getController() {
        synchronized (lock) {
            if (RequestingProtocolMessageParser.controller != null) {
                return RequestingProtocolMessageParser.controller;
            }
        }
        return new DashboardController();
    }

    private static WorkflowDashboardController getController2() {
        synchronized (lock) {
            if (RequestingProtocolMessageParser.theController != null) {
                return RequestingProtocolMessageParser.theController;
            }
        }
        return new WorkflowDashboardController();
    }

    /**
     * Parse and build the request.
     *
     * @param code
     * @param sIn
     * @return
     */
    public static RequestingProtocolRequest parse(final int code, DataInputStream sIn) throws IOException {
        // as a fallback make sure we return unknown
        RequestingProtocolRequest request = new UnknownRequest(code);

        if (10 == code) {
            System.out.println("Received a message to send info about workflow");
            request = parseGetWorkflowState(code, sIn);
        } else if (11 == code) {
            System.out.println("Received a message to send info for collaborator dashboard");
            request = parseGetCollaboratorDashboard(code, sIn);
        }
        return request;
    }

    private static RequestingProtocolRequest parseGetCollaboratorDashboard(int code ,DataInputStream sIn) throws IOException {
        String collaborator;
        int size = sIn.readByte();
        byte[] messageByte = new byte[size];
        boolean end = false;
        StringBuilder dataString = new StringBuilder(size);
        int totalBytesRead = 0;
        while (!end) {
            int currentBytesRead = sIn.read(messageByte, 0, size);
            totalBytesRead += currentBytesRead;
            if (totalBytesRead <= size) {
                dataString.append(new String(messageByte, 0, currentBytesRead, StandardCharsets.UTF_8));
            } else {
                dataString.append(new String(messageByte, 0, size - totalBytesRead + currentBytesRead, StandardCharsets.UTF_8));
            }
            if (dataString.length() >= size) {
                end = true;
            }
        }
        collaborator = dataString.toString();
        RequestingProtocolRequest request;

        request = new GetDashboardDataRequest(getController(), code, collaborator);
        return request;
    }

    private static RequestingProtocolRequest parseGetWorkflowState(int code, DataInputStream sIn) throws IOException {
        RequestingProtocolRequest request;
        request = new GetWorkflowStateRequest(getController2(), code);
        return request;
    }

}
