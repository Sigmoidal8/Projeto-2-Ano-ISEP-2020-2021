package eapli.base.app.user.console.presentation.request;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.requestmanagement.application.GiveFeedbackController;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.List;

/**
 * @author Raúl Coelho
 */
public class GiveFeedbackUI extends AbstractUI {

    private final GiveFeedbackController theController = new GiveFeedbackController();

    @Override
    protected boolean doShow() {
        final Iterable<Request> requests = this.theController.availableRequestsFeedback();
        final SelectWidget<Request> selector = new SelectWidget<>("Select the request to give Feedback\nRequests Available:", requests,
                new RequestPrinter());
        selector.show();
        final Request theRequest = selector.selectedElement();

        if(theRequest != null) {
            List<TaskExecution> taskExecutions = theRequest.taskExecutions();

            for (TaskExecution task : taskExecutions) {
                System.out.println(task.metrics().showResolutionTime());
            }

            int value = 0;
            do {
                value = Console.readInteger("What value you rate this request? (0-5)");
            } while (value < 0 || value > 5);

            theController.giveFeedback(theRequest, value);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String headline() {
        return "Give Feedback";
    }
}
