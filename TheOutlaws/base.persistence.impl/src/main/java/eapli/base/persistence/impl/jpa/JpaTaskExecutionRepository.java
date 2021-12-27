package eapli.base.persistence.impl.jpa;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.criticitymanagement.domain.Criticity;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.base.taskmanagement.repositories.TaskExecutionRepository;

import javax.persistence.TypedQuery;

/**
 * @author Raúl Coelho
 */
public class JpaTaskExecutionRepository extends BasepaRepositoryBase<TaskExecution, Long, Long> implements TaskExecutionRepository {

    public JpaTaskExecutionRepository() {
        super("id");
    }

    @Override
    public Iterable<TaskExecution> findAvailableTasks(Collaborator colaborator) {
        final TypedQuery<TaskExecution> query = entityManager().createQuery(""
                + "SELECT te FROM TaskExecution te INNER JOIN Task t on te.responsibleCollaborator = null AND te.taskStatus = 0 AND t = te.task "
                + "INNER JOIN Service ser on ser.type = 0 INNER JOIN Workflow w on ser.workflows = w INNER JOIN w.tasks wt on wt = t ", TaskExecution.class);
        //query.setParameter("colab", colaborator);
        return query.getResultList();
    }

    @Override
    public Catalog findTaskCatalog(TaskExecution task) {
        final TypedQuery<Catalog> query = entityManager().createQuery(""
                + "SELECT c FROM TaskExecution te INNER JOIN Task t on t = te.task AND te =: task1 "
                + "INNER JOIN Service ser on t EXISTS (ser.workflows.tasks) "
                + "INNER JOIN Catalog c on ser EXISTS (c.services)", Catalog.class);
        query.setParameter("task1", task);
        return query.getSingleResult();
    }

    @Override
    public Iterable<TaskExecution> findColaboratorTasks(Collaborator colaborator) {
        final TypedQuery<TaskExecution> query = entityManager().createQuery(""
                + "SELECT te FROM TaskExecution te INNER JOIN Collaborator co on co =: colab  AND te.responsibleCollaborator = co  "
                + "AND te.taskStatus <> 1", TaskExecution.class);
        query.setParameter("colab", colaborator);
        return query.getResultList();
    }

    @Override
    public Request findRequestTasks(TaskExecution taskExecution) {
        final TypedQuery<Request> query = entityManager().createQuery(""
                + "SELECT r FROM Request r INNER JOIN r.taskExecutions te ON te =: taskExecution", Request.class);
        query.setParameter("taskExecution", taskExecution);
        return query.getSingleResult();
    }

    @Override
    public Iterable<TaskExecution> findColaboratorPendingTasks(Collaborator collaborator) {
        final TypedQuery<TaskExecution> query = entityManager().createQuery(""
                + "SELECT te FROM TaskExecution te INNER JOIN Collaborator co on co =: colab  AND te.responsibleCollaborator = co "
                + "AND te.taskStatus = 0", TaskExecution.class);
        query.setParameter("colab", collaborator);
        return query.getResultList();
    }
    
     @Override
    public Iterable<TaskExecution> findAllColaboratorTasks(Collaborator colaborator) {
        final TypedQuery<TaskExecution> query = entityManager().createQuery(""
                + "SELECT te FROM TaskExecution te INNER JOIN Collaborator co on co =: colab  AND te.responsibleCollaborator = co ", TaskExecution.class);
        query.setParameter("colab", colaborator);
        return query.getResultList();
    }
}
