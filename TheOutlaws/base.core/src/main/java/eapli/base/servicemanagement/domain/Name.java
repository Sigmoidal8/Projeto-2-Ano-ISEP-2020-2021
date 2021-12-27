/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.servicemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import javax.persistence.Embeddable;

/**
 *
 * @author Utilizador
 */
@Embeddable
public class Name implements ValueObject, Comparable<Name> {

    private String name;

    public Name(String name) {
        this.name = name;
    }

    protected Name() {
        //ORM only
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }

        final Name that = (Name) o;
        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public int compareTo(final Name o) {
        return name.compareTo(o.name);
    }

    public String toString(){
        return String.format("%s", name);
    }

}
