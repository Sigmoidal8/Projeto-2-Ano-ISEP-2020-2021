package protocol;

import eapli.base.dashboardmanagement.application.DashboardController;
import eapli.base.taskmanagement.domain.TaskExecution;

/**
 * @author Raúl Coelho
 */
public class GetActiveTasksRequest extends TaskExecutorProtocolRequest{

    public GetActiveTasksRequest(final TaskExecutorController controller, final int request){
        super(controller,request);
    }

    @Override
    public String execute() {
        try {
            final int activeThreads = controller.activeThreads();
            return String.format("%d",activeThreads);
        } catch (final Exception e) {
            // we should be careful about exposing the Exception to the outside!
            return buildServerError(e.getMessage());
        }
    }
}
