package eapli.base.taskmanagement.domain;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.servicemanagement.domain.Form;
import eapli.base.teammanagement.domain.Team;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.List;

/**
 * @author Raúl Coelho
 */
@Entity
public class ManualTask extends Task{

    @ManyToOne(cascade=CascadeType.ALL)
    private Form form;

    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(name = "Task_TeamsAplicable")
    private List<Team> teams;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "Task_CollaboratorsAplicable")
    private List<Collaborator> collaborators;

    public ManualTask(TaskType type, int index, Form form, List<Team> teams){
        super(type, index);
        Preconditions.noneNull(form, teams);
        this.form = form;
        this.teams = teams;
    }

    public ManualTask(TaskType type, int index, List<Collaborator> collaborators,Form form){
        super(type, index);
        Preconditions.noneNull(collaborators, form);
        this.form = form;
        this.collaborators = collaborators;
    }

    protected ManualTask(){
        //ORM only
    }
    
    public Form forms(){
        return this.form;
    }

    @Override
    public String toString(){
        return String.format("%s , Manual Task", super.toString());
    }
}
