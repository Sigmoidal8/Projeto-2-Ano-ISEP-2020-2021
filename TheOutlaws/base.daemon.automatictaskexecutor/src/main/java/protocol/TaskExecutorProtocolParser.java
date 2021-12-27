package protocol;

import eapli.base.dashboardmanagement.application.DashboardController;
import eapli.base.dashboardmanagement.workflow.application.WorkflowDashboardController;
import eapli.base.requestmanagement.domain.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Raúl Coelho
 */
public class TaskExecutorProtocolParser {

    private static final Logger LOGGER = LogManager.getLogger(TaskExecutorProtocolParser.class);

    private static TaskExecutorController theController;

    private TaskExecutorProtocolParser() {
        // avoid instantiation
    }

    /**
     * To inject a different controller for testing purposes.
     *
     * @param controller
     */

    /* package */
    public static void injectController(final TaskExecutorController controller) {
        synchronized (lock) {
            TaskExecutorProtocolParser.theController = controller;
        }
    }

    private static final Object lock = new Object();

    private static TaskExecutorController getController() {
        synchronized (lock) {
            if (TaskExecutorProtocolParser.theController != null) {
                return TaskExecutorProtocolParser.theController;
            }
        }
        return new TaskExecutorController();
    }


    /**
     * Parse and build the request.
     *
     * @param code
     * @param sIn
     * @return
     */
    public static TaskExecutorProtocolRequest parse(final int code, DataInputStream sIn) throws IOException, ClassNotFoundException {
        // as a fallback make sure we return unknown
        TaskExecutorProtocolRequest request = new UnknownRequest(code);

        if (20 == code) {
            System.out.println("Received a message to send info about active tasks");
            request = parseSendActiveTasks(code, sIn);
        } else if (21 == code) {
            System.out.println("Received a message to execute a task");
            request = parseExecuteTask(code, sIn);
        }
        return request;
    }

    private static TaskExecutorProtocolRequest parseSendActiveTasks(int code ,DataInputStream sIn) throws IOException {
        TaskExecutorProtocolRequest request = new GetActiveTasksRequest(getController(), code);
        return request;
    }

    private static TaskExecutorProtocolRequest parseExecuteTask(int code , DataInputStream sIn) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(sIn);
        Request requestRead = (Request) in.readObject();

        TaskExecutorProtocolRequest request = new ExecuteTaskRequest(getController(), code, requestRead);
        return request;
    }
}
