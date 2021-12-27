package eapli.base.taskmanagement.repositories;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.criticitymanagement.domain.Criticity;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.taskmanagement.domain.Task;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.framework.domain.repositories.DomainRepository;

/**
 * @author Raúl Coelho
 */
public interface TaskExecutionRepository extends DomainRepository<Long, TaskExecution> {

    Iterable<TaskExecution> findAvailableTasks(Collaborator colaborator);

    Catalog findTaskCatalog(TaskExecution task);

    Iterable<TaskExecution> findColaboratorTasks(Collaborator colaborator);

    Request findRequestTasks(TaskExecution taskExecution);

    Iterable<TaskExecution> findColaboratorPendingTasks(Collaborator collaborator);

    Iterable<TaskExecution> findAllColaboratorTasks(Collaborator collaborator);
}
