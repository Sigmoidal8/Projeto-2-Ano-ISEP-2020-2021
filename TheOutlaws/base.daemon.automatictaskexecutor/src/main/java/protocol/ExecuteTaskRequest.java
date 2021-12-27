package protocol;

import eapli.base.requestmanagement.domain.Request;
import eapli.base.taskmanagement.domain.TaskExecution;

/**
 * @author Raúl Coelho
 */
public class ExecuteTaskRequest extends TaskExecutorProtocolRequest{

    private Request requestData;

    public ExecuteTaskRequest(final TaskExecutorController controller, final int request,Request requestData){
        super(controller, request);
        this.requestData = requestData;
    }

    @Override
    public String execute() {
        try {
            System.out.println(requestData);
            final String executionEnded = controller.executeTask(requestData);
            System.out.println(executionEnded);
            return executionEnded;
        } catch (final Exception e) {
            // we should be careful about exposing the Exception to the outside!
            return buildServerError(e.getMessage());
        }
    }
}
