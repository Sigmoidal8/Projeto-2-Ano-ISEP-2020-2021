package protocol;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.dashboardmanagement.workflow.application.WorkflowDashboardController;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.requestmanagement.repositories.RequestRepository;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.framework.csv.CsvRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Raúl Coelho
 */
public class GetWorkflowStateRequest extends RequestingProtocolRequest {

    private final RequestRepository requestRepository = PersistenceContext.repositories().requests();

    public GetWorkflowStateRequest(final WorkflowDashboardController controller, final int request) {
        super(controller, request);
    }

    @Override
    public String execute() {
        try {
            final Iterable<Request> requests = requestRepository.findAll();
            System.out.println(requests);
            return buildResponse(requests);
        } catch (final Exception e) {
            // we should be careful about exposing the Exception to the outside!
            return buildServerError(e.getMessage());
        }
    }

    private String buildResponse(final Iterable<Request> requests) {

        final var sb = new StringBuilder();

        // header
        sb.append("\"CODE\", \"DATE\", \"DEADLINE\", \"TYPE\", \"PRIORITY\", \"STATUS\",\"SERVICE_CODE\",\"REQUESTER\"");
        sb.append(";");

        // result rows
        for (final Request each : requests) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            sb.append(
                    CsvRecord.valueOf(new Object[]{
                            each.uniqueCode(),
                            each.dateOfRequestt(),
                            each.deadLineDate(),
                            each.type(),
                            each.priority(),
                            each.status(),
                            each.service(),
                            each.collaborator().completeName()
                    }).toString());
            // end of line
            sb.append(";");
        }

        // end of message
        //sb.append(";");

        return sb.toString();
    }
}
