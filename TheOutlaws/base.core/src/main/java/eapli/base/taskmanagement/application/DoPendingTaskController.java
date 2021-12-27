/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.taskmanagement.application;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.requestmanagement.domain.RequestStatus;
import eapli.base.servicemanagement.domain.Attribute;
import eapli.base.taskmanagement.domain.ApprovalTask;
import eapli.base.taskmanagement.domain.ManualTask;
import eapli.base.requestmanagement.repositories.RequestRepository;
import eapli.base.taskmanagement.domain.Metrics;
import eapli.base.taskmanagement.domain.Task;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.base.taskmanagement.domain.TaskStatus;
import eapli.base.taskmanagement.domain.TaskType;
import eapli.base.taskmanagement.repositories.TaskExecutionRepository;
import eapli.base.taskmanagement.repositories.TaskRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author migue
 */
public class DoPendingTaskController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TaskExecutionRepository taskRepository = PersistenceContext.repositories().taskExecutions();
    private final RequestRepository requestRepository = PersistenceContext.repositories().requests();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();

    public List<TaskExecution> searchPendingTasks() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.HR_MANAGER, BaseRoles.COLLABORATOR, BaseRoles.SERVICES_MANAGER);

        Collaborator collaborator = null;
        Optional<Collaborator> optional = collaboratorRepository.findByEmail(authz.session().get().authenticatedUser().email());

        if (optional.get() != null) {
            collaborator = optional.get();
        }

        Iterable<TaskExecution> tasksAux = taskRepository.findColaboratorPendingTasks(collaborator);
        List<TaskExecution> tasks = new ArrayList<>();

        if (tasks == null) {
            return null;
        }
        for (TaskExecution t : tasksAux) {
            tasks.add(t);
        }
        return tasks;
    }

    public TaskExecution doApprovalTask(TaskExecution task, List<String> formResponse) {

        task.updateFormsData(formResponse);
        task.changeStatus(TaskStatus.completed);

        return taskRepository.save(task);
    }

    public Request doApprovalTask(TaskExecution task, boolean isTaskApproved) {
        Request request = taskRepository.findRequestTasks(task);

        if (isTaskApproved) {
            request.changeStatus(RequestStatus.Aproved);
        } else {
            request.changeStatus(RequestStatus.Rejected);
        }

        return requestRepository.save(request);
    }

    public TaskExecution doResolutionTask(TaskExecution task, List<String> formResponse) {

        task.updateFormsData(formResponse);
        task.changeStatus(TaskStatus.completed);

        return taskRepository.save(task);
    }

    public int verifyTaskType(TaskExecution task) {
        int taskType = 0;
        if (task.task().type() == TaskType.Approval) {
            taskType = 1;
        } else if (task.task().type() == TaskType.Realization) {
            taskType = 2;
        }
        return taskType;
    }

    public TaskExecution calculateResolutionTime(TaskExecution task) throws ParseException {
        Date currentdate = new Date();
        Date resolutionTime = task.metrics().resolutionTime();

        long diffInMillies = Math.abs(currentdate.getTime() - resolutionTime.getTime());

        Date date = new Date(diffInMillies);
        Metrics metrics = new Metrics(date);
        task.inputMetrics(metrics);

        return taskRepository.save(task);
    }

    public void showRequestDetails(TaskExecution task) {
        Request request = taskRepository.findRequestTasks(task);
        System.out.println("\nRequest Details:");
        System.out.println("================");
        System.out.printf("Unique Code: %s;\nService: %s;\nDate Of Request: %s;\nDeadLine Date: %s;\nType: %s;\nPriority: %s;\nStatus: %s;\nTasks Executions: %s;\n",
                request.uniqueCode(), request.service(), request.dateOfRequestt(), request.deadLineDate(), request.type(), request.priority(),
                request.status(), request.taskExecutions());
        System.out.println("================\n");
    }

    public void showFormResponse(TaskExecution task, boolean isApproval) {
        Request request = taskRepository.findRequestTasks(task);
        System.out.println("Request Form:");
        System.out.println("================");
        System.out.println(request.service().forms().atributes());
        System.out.println(request.formResponse());
        System.out.println("================\n");
        if (!isApproval) {
            TaskExecution approvalTaskExecution = request.approvalTask();
            if (approvalTaskExecution != null) {
                ApprovalTask approvalTask = (ApprovalTask) approvalTaskExecution.task();
                List<Attribute> atributes = approvalTask.forms().atributes();
                System.out.println("Approval Form:");
                System.out.println("================");
                System.out.println(atributes.toString());
                System.out.println(approvalTaskExecution.formResponse());
                System.out.println("================\n");
            }
        }
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
