/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.taskmanagement.application;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.taskmanagement.domain.Task;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.base.taskmanagement.repositories.TaskExecutionRepository;
import eapli.base.taskmanagement.repositories.TaskRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author
 */
public class ClaimTaskController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TaskExecutionRepository taskRepository = PersistenceContext.repositories().taskExecutions();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();

    public Iterable<TaskExecution> findAvailableTasks() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.HR_MANAGER, BaseRoles.COLLABORATOR, BaseRoles.SERVICES_MANAGER);

        Collaborator collaborator = null;
        Optional<Collaborator> optional = collaboratorRepository.findByEmail(authz.session().get().authenticatedUser().email());

        if (optional.get() != null) {
            collaborator = optional.get();
        }

        Iterable<TaskExecution> tasks = taskRepository.findAvailableTasks(collaborator);

        if (tasks == null) {
            return null;
        }

        return tasks;
    }

    public TaskExecution claimTask(TaskExecution task) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.HR_MANAGER, BaseRoles.COLLABORATOR, BaseRoles.SERVICES_MANAGER);

        Optional<Collaborator> optional = collaboratorRepository.findByEmail(authz.session().get().authenticatedUser().email());

        if (optional.get() != null) {
            task.changeResponsibleCollaborator(optional.get());
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            task.changeClaimTaskDate(date);
//            task.changeClaimTaskDate(formatter.format(date));
        }

        return taskRepository.save(task);
    }

    public Iterable<TaskExecution> orderByPriority(Iterable<TaskExecution> tasks) {
        List<TaskExecution> tasksAux = new ArrayList<>();

        tasks.forEach(tasksAux::add);

        Collections.sort(tasksAux, new Comparator<TaskExecution>() {
            @Override
            public int compare(TaskExecution t1, TaskExecution t2) {
                Request request = taskRepository.findRequestTasks(t1);
                Request request2 = taskRepository.findRequestTasks(t2);
                return request.priority().compareTo(request2.priority());
            }
        });
        return tasksAux;
    }

    public Iterable<TaskExecution> orderByCriticality(Iterable<TaskExecution> tasks) {
        List<TaskExecution> tasksAux = new ArrayList<>();

        tasks.forEach(tasksAux::add);

        for (int i = 1; i < tasksAux.size(); i++) {
            Request r = taskRepository.findRequestTasks(tasksAux.get(i));
            for (int j = 0; j < i; j++) {
                Request r2 = taskRepository.findRequestTasks(tasksAux.get(j));
                if (r.service().criticality().value().Value() > r2.service().criticality().value().Value()) {
                    TaskExecution temp = tasksAux.get(i);
                    tasksAux.remove(i);
                    tasksAux.add(i, tasksAux.get(j));
                    tasksAux.remove(j);
                    tasksAux.add(j, temp);
                }
            }
        }
        return tasksAux;
    }

    public Iterable<TaskExecution> orderByCompletationDeadline(Iterable<TaskExecution> tasks) {
        List<TaskExecution> tasksAux = new ArrayList<>();

        tasks.forEach(tasksAux::add);

        for (int i = 1; i < tasksAux.size(); i++) {
            for (int j = 0; j < i; j++) {
                Request request = taskRepository.findRequestTasks(tasksAux.get(i));
                Request request2 = taskRepository.findRequestTasks(tasksAux.get(j));
                if (request.deadLineDate().compareTo(request2.deadLineDate()) > 0) {
                    TaskExecution temp = tasksAux.get(i);
                    tasksAux.remove(i);
                    tasksAux.add(i, tasksAux.get(j));
                    tasksAux.remove(j);
                    tasksAux.add(j, temp);
                }
            }
        }
        return tasksAux;
    }

    public Iterable<TaskExecution> filterByPriority(Iterable<TaskExecution> tasks, String priority) {
        List<TaskExecution> tasksAux = new ArrayList<>();
        for (TaskExecution t : tasks) {
            Request request = taskRepository.findRequestTasks(t);
            if (request.priority().toString().equals(priority)) {
                tasksAux.add(t);
            }
        }
        return tasksAux;
    }

    public Iterable<TaskExecution> filterByCriticality(Iterable<TaskExecution> tasks, int criticality) {
        List<TaskExecution> tasksAux = new ArrayList<>();
        for (TaskExecution t : tasks) {
            Request r = taskRepository.findRequestTasks(t);
            if (r.service().criticality().value().Value() == criticality) {
                tasksAux.add(t);
            }
        }
        return tasksAux;
    }

    public Iterable<TaskExecution> filterByCompletationDeadline(Iterable<TaskExecution> tasks, Date deadline) {
        List<TaskExecution> tasksAux = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date aux = new Date();
        String dead = sdf.format(deadline);
        try {
            aux = new SimpleDateFormat("dd/MM/yyyy").parse(dead);

        } catch (ParseException ex) {
        }

        for (TaskExecution t : tasks) {
            Request request = taskRepository.findRequestTasks(t);
            if (request.deadLineDate().date().compareTo(aux) == 0) {
                tasksAux.add(t);
            }
        }
        return tasksAux;
    }

}
