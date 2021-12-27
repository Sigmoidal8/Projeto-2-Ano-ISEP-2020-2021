/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.criticitymanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;
import javax.persistence.Embeddable;

/**
 *
 * @author migue
 */
@Embeddable
public class Value implements ValueObject, Comparable<Value> {

    private static final long serialVersionUID = 1L;

    private Integer value;

    public Value(int value) {
        Preconditions.noneNull(value);
        Preconditions.ensure(value != 0);
        // TODO validate invariants, i.e., mecanographic number regular
        // expression
        this.value = value;
    }

    protected Value() {
        // for ORM
    }

    public static Value valueOf(final int value) {
        return new Value(value);
    }
    
    public Integer Value() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Value)) {
            return false;
        }

        final Value that = (Value) o;
        return this.value ==  that.value;
    }

    @Override
    public int compareTo(Value o) {
        return value.compareTo(o.value); 
    }
    
    public String toString() {
        return String.format("%d", value);
    }
}
