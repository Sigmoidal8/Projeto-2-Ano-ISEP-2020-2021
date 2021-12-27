package protocol;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.repositories.CatalogRepository;
import eapli.base.dashboardmanagement.application.DashboardController;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.base.taskmanagement.repositories.TaskExecutionRepository;
import eapli.framework.csv.CsvRecord;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Raúl Coelho
 */
public class GetDashboardDataRequest extends RequestingProtocolRequest {

    private final String collaborator;
    private final TaskExecutionRepository taskExecutionRepository = PersistenceContext.repositories().taskExecutions();
    private final CatalogRepository catalogRepository = PersistenceContext.repositories().catalogs();

    public GetDashboardDataRequest(final DashboardController controller, final int request, final String collaborator) {
        super(controller, request);
        this.collaborator = collaborator;
    }

    @Override
    public String execute() {
        try {
            final Iterable<TaskExecution> meals = controller.getColabTasks(collaborator);
            return buildResponse(meals);
        } catch (final Exception e) {
            // we should be careful about exposing the Exception to the outside!
            return buildServerError(e.getMessage());
        }
    }

    private String buildResponse(final Iterable<TaskExecution> taskExecutions) {

        final var sb = new StringBuilder();

        // header
        sb.append("\"TYPE\", \"SERVICE_CODE\", \"REQUEST_ID\", \"REQUEST_DATE\", \"PRIORITY\", \"CRITICALITY\",\"REQUESTER\",\"ENDING_TIME\"");
        sb.append(";");

        // result rows
        for (final TaskExecution each : taskExecutions) {
            Request request = taskExecutionRepository.findRequestTasks(each);
            Catalog catalog = catalogRepository.findByService(request.service());

            Date currentDate = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(new Date());
            try {
                currentDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);

            } catch (ParseException ex) {
            }

            int aux = request.deadLineDate().date().compareTo(currentDate);

            if (aux > 0) {
                sb.append(
                        CsvRecord.valueOf(new Object[]{
                    each.task().type(),
                    request.service().identity(),
                    request.identity(),
                    request.dateOfRequestt(),
                    request.priority(),
                    catalog.Criticality().value(),
                    request.collaborator().completeName(),
                    calculateRemainTime(request.deadLineDate().date().toString()) + " Days Remaining"
                }).toString());
            } else {
                sb.append(
                        CsvRecord.valueOf(new Object[]{
                    each.task().type(),
                    request.service().identity(),
                    request.identity(),
                    request.dateOfRequestt(),
                    request.priority(),
                    catalog.Criticality().value(),
                    request.collaborator().completeName(),
                    calculateRemainTime(request.deadLineDate().date().toString()) + " Days Delayed"
                }).toString());
            }
            // end of line
            sb.append(";");
        }

        // end of message
        //sb.append(";");

        return sb.toString();
    }

    public static long calculateRemainTime(String scheduled_date) {

        // date format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        // two dates
        java.util.Date scheduledDate;
        Calendar current = Calendar.getInstance();
        java.util.Date currentDate;
        String current_date = format.format(current.getTime());
        long diffence_in_days = 0;
        try {
            scheduledDate = format.parse(scheduled_date);
            currentDate = format.parse(current_date);
            long diffInMillies = scheduledDate.getTime() - currentDate.getTime();
            diffence_in_days = Math.abs(TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS));
            return diffence_in_days;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diffence_in_days;
    }

    public Iterable<TaskExecution> orderByPriority(Iterable<TaskExecution> tasks) {
        List<TaskExecution> tasksAux = new ArrayList<>();
        Collections.sort(tasksAux, new Comparator<TaskExecution>() {
            @Override
            public int compare(TaskExecution t1, TaskExecution t2) {
                Request request = taskExecutionRepository.findRequestTasks(t1);
                Request request2 = taskExecutionRepository.findRequestTasks(t2);
                return request.priority().compareTo(request2.priority());
            }
        });
        return tasksAux;
    }

    public Iterable<TaskExecution> orderByCriticality(Iterable<TaskExecution> tasks) {
        List<TaskExecution> tasksAux = new ArrayList<>();

        for (int i = 1; i < tasksAux.size(); i++) {
            Catalog c = taskExecutionRepository.findTaskCatalog(tasksAux.get(i));
            for (int j = 0; j < i; j++) {
                Catalog c2 = taskExecutionRepository.findTaskCatalog(tasksAux.get(j));
                if (c.Criticality().value().Value() > c2.Criticality().value().Value()) {
                    TaskExecution temp = tasksAux.get(i);
                    tasksAux.add(i, tasksAux.get(j));
                    tasksAux.add(j, temp);
                }
            }
        }
        return tasksAux;
    }

    public Iterable<TaskExecution> orderByCompletationDeadline(Iterable<TaskExecution> tasks) {

        List<TaskExecution> tasksAux = new ArrayList<>();

        for (int i = 1; i < tasksAux.size(); i++) {
            for (int j = 0; j < i; j++) {
                Request request = taskExecutionRepository.findRequestTasks(tasksAux.get(i));
                Request request2 = taskExecutionRepository.findRequestTasks(tasksAux.get(j));
                if (request.deadLineDate().compareTo(request2.deadLineDate()) > 0) {
                    TaskExecution temp = tasksAux.get(i);
                    tasksAux.add(i, tasksAux.get(j));
                    tasksAux.add(j, temp);
                }
            }
        }
        return tasksAux;
    }

}
