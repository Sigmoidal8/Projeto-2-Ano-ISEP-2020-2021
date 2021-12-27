package eapli.base.workflowmanagement.domain;

import eapli.base.taskmanagement.domain.Task;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Workflow implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    private WorkflowStatus status;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="workflow_tasks")
    @OrderColumn(name="index")
    private List<Task> tasks;

    public Workflow(final Date startDate, final WorkflowStatus status){
        this.startDate=startDate;
        this.status=status;
        this.tasks=new ArrayList<>();
    }

    protected Workflow(){
        //ORM only
    }

    public List<Task> tasks(){
        return tasks;
    }

    @Override
    public boolean sameAs(Object other) {

        if (!(other instanceof Workflow)) {
            return false;
        }

        final Workflow that = (Workflow) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public Long identity() {
        return id;
    }

    public String toString(){
        return String.format("Workflow %d, StartDate:%s -Status:%s", id, startDate, status);
    }
}
