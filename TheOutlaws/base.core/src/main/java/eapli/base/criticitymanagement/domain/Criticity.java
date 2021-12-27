/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.criticitymanagement.domain;

import eapli.base.teammanagement.domain.Color;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import javax.persistence.*;

/**
 *
 * @author miguel
 */
@Entity
public class Criticity implements AggregateRoot<Value> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    private Value value;
    
    private Label label;
    
    private Color color;

    private Objective objective;

    public Criticity(Label label, Value value, Color color, Objective objective) {
        Preconditions.noneNull(label,color,objective);
        //Preconditions.ensure(value != 0);
        
        this.label = label;
        this.value = value;
        this.color = color;
        this.objective = objective;
    }

    protected Criticity() {
        //ORM only
    }

    public Objective objectives(){
        return objective;
    }
    
    public Value value(){
        return value;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Criticity)) {
            return false;
        }

        final Criticity that = (Criticity) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public Value identity() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.format("Label: %s, Value: %s, Color: %s, Objectives: %s", label, value, color, objective);
    }
}
