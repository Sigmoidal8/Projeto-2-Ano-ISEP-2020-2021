package eapli.base.taskmanagement.domain;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.requestmanagement.domain.Request;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * @author Raúl Coelho
 */
@Entity
public class TaskExecution implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Task task;

    private TaskStatus taskStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    private Collaborator responsibleCollaborator;

    private Metrics metrics;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> formResponse;
    
    private Date claimTaskDate;

    public TaskExecution(Task task, TaskStatus taskStatus) {

        Preconditions.noneNull(task, taskStatus);

        this.task = task;
        this.taskStatus = taskStatus;
    }

    protected TaskExecution() {
        //ORM Only
    }

    public TaskStatus status() {
        return this.taskStatus;
    }

    public Task task() {
        return this.task;
    }

    public Metrics metrics() {
        return this.metrics;
    }
    
    public Date claimTaskDate() {
        return this.claimTaskDate;
    }

    public List<String> formResponse(){
        return  formResponse;
    }

    public TaskExecution changeResponsibleCollaborator(Collaborator collaborator){
        this.responsibleCollaborator = collaborator;
        return this;
    }

    public TaskExecution inputMetrics(Metrics metrics) {
        this.metrics = metrics;
        return this;
    }
    
    public TaskExecution updateFormsData(List<String> formResponse) {
        this.formResponse = formResponse;
        return this;
    }

    public TaskExecution changeStatus(TaskStatus status) {
        this.taskStatus = status;
        return this;
    }
    
    public TaskExecution changeClaimTaskDate(Date date) {
        this.claimTaskDate = date;
        return this;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof TaskExecution)) {
            return false;
        }

        final TaskExecution that = (TaskExecution) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public Long identity() {
        return id;
    }

    public String toString() {
        return String.format("%s", task.toString());
    }
}
