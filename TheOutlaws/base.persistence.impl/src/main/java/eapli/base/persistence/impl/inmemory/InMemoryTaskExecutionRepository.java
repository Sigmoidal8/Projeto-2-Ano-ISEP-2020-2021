package eapli.base.persistence.impl.inmemory;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.taskmanagement.domain.Task;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.base.taskmanagement.repositories.TaskExecutionRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

/**
 * @author Raúl Coelho
 */
public class InMemoryTaskExecutionRepository extends InMemoryDomainRepository<TaskExecution, Long> implements TaskExecutionRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<TaskExecution> findAvailableTasks(Collaborator colaborator) {
        return null;
    }

    @Override
    public Catalog findTaskCatalog(TaskExecution task) {
        return null;
    }
    
    @Override
    public Iterable<TaskExecution> findColaboratorTasks(Collaborator colaborator) {
        return null;
    }

    @Override
    public Request findRequestTasks(TaskExecution taskExecution) {
        return null;
    }
    
     @Override
    public Iterable<TaskExecution> findColaboratorPendingTasks(Collaborator collaborator) {
        return null;
    }
    
    @Override
    public Iterable<TaskExecution> findAllColaboratorTasks(Collaborator collaborator) {
        return null;
    }
}
