/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.requestmanagement.application;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.repositories.CatalogRepository;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.requestmanagement.domain.Priority;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.requestmanagement.domain.RequestDate;
import eapli.base.requestmanagement.domain.RequestStatus;
import eapli.base.requestmanagement.domain.UniqueCode;
import eapli.base.requestmanagement.repositories.RequestRepository;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.Type;
import eapli.base.taskmanagement.domain.ApprovalTask;
import eapli.base.taskmanagement.domain.Task;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.base.taskmanagement.domain.TaskStatus;
import eapli.base.taskmanagement.domain.TypeCollaboratorApproval;
import eapli.base.taskmanagement.repositories.TaskExecutionRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author José Forno
 */
public class RequestServiceController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RequestRepository requestRepository = PersistenceContext.repositories().requests();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();
    private final TaskExecutionRepository taskExecutionRepository = PersistenceContext.repositories().taskExecutions();
    private final CatalogRepository catalogRepository = PersistenceContext.repositories().catalogs();

    public Request requestService(Priority priority, Date deadline, List<String> files, Service service, int isDraft, List<String> formResponse) {

        String uniqueCode = generateUniqueCode();

        Collaborator collaborator = null;
        Optional<Collaborator> optional = collaboratorRepository.findByEmail(authz.session().get().authenticatedUser().email());
        if (optional.get() != null) {
            collaborator = optional.get();
        }

        Date dateOfRequest = currentDate();

        Type type = service.type();

        RequestStatus status = RequestStatus.Submited;

        //Request newRequest = new Request(new UniqueCode(uniqueCode),service, collaborator,RequestDate.valueOf(dateOfRequest),type,priority,RequestDate.valueOf(deadline), files,status,isDraft,formResponse);
        //Request r = requestRepository.save(newRequest);
        List<Task> taskL = service.workflows().tasks();
        TaskExecution taskExecutionAux;
        List<TaskExecution> taskExecutionList = new ArrayList<>();
        for (Task t : taskL) {
            taskExecutionAux = createTaskExecution(TaskStatus.waiting, t, service, collaborator);

            taskExecutionList.add(taskExecutionAux);
        }

        Request newRequest = new Request(new UniqueCode(uniqueCode), service, collaborator, RequestDate.valueOf(dateOfRequest), type, priority,
                RequestDate.valueOf(deadline), files, status, isDraft, formResponse, taskExecutionList);

        Request r = requestRepository.save(newRequest);

        return r;

    }

    public TaskExecution createTaskExecution(TaskStatus ts, Task t, Service service, Collaborator collaborator) {

        TaskExecution te = new TaskExecution(t, ts);

        try {
            ApprovalTask aprov = (ApprovalTask) t;

            if (aprov.collaboratorForApproval() == TypeCollaboratorApproval.CatalogResponsibleCollaborator) {

                Collaborator colab = (catalogRepository.findByService(service)).collaborator();
                te.changeResponsibleCollaborator(colab);
                te = taskExecutionRepository.save(te);
            } else if (aprov.collaboratorForApproval() == TypeCollaboratorApproval.HierarchyCollaborator) {

                Collaborator colab = collaborator.collaboratorResponsible();
                if (colab == null) {
                    System.out.println("Collaborator doesn't have a responsible");
                    return null;
                }
                te.changeResponsibleCollaborator(colab);
                te = taskExecutionRepository.save(te);

            }

            return te;

        } catch (ClassCastException ex) {
            return taskExecutionRepository.save(te);
        }
    }

    public String generateUniqueCode() {
        List<Request> listaRequests = (List<Request>) requestRepository.findAll();

        int size = listaRequests.size();

        int year = Calendar.getInstance().get(Calendar.YEAR);

        DecimalFormat df = new DecimalFormat("00000");

        String numero = df.format(size+1);

        String codigo = year + "/" + numero;

        return codigo;
    }

    public Date currentDate() {
        Date date = new Date();

        return date;
    }
    
    public boolean isValidByExpressaoRegular(String string1, String expressaoRegular) {


        String regex = "^" + expressaoRegular + "$";

  
        Pattern p = Pattern.compile(regex);
        if (string1 == null) {
            return false;
        }

        Matcher m = p.matcher(string1);

        return m.matches();
    }

}
