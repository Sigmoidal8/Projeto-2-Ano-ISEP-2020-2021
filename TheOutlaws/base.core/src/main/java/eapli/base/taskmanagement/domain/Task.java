/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.taskmanagement.domain;

import javax.persistence.*;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.servicemanagement.domain.Form;
import eapli.base.servicemanagement.domain.Script;
import eapli.base.teammanagement.domain.Team;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import java.util.List;

/**
 *
 * @author migue
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Task implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uniqueCode;

    private TaskType type;

    private int index;

    public Task(TaskType type, int index) {
        Preconditions.noneNull(type);
        this.type = type;
        this.index = index;
    }


    protected Task() {
        //ORM only
    }

    public TaskType type(){
        return type;
    }

    public Task changeIndex(int index){
        this.index=index;
        return this;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Task)) {
            return false;
        }

        final Task that = (Task) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public Long identity() {
        return uniqueCode;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", uniqueCode, type);
    }
}
