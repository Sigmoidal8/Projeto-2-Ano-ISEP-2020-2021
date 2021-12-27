package eapli.base.taskmanagement.domain;

import eapli.base.servicemanagement.domain.Script;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;

import javax.persistence.Entity;

/**
 * @author Raúl Coelho
 */
@Entity
public class AutomaticTask extends Task {

    private Script script;

    public AutomaticTask(TaskType type, int index, Script script){
        super(type, index);
        Preconditions.noneNull(script);
        this.script = script;
    }

    protected AutomaticTask(){
        //ORM only
    }

    public Script script(){
        return script;
    }
}
