/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.teammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import javax.persistence.Embeddable;

/**
 *
 * @author José Forno
 */
@Embeddable
public class Designation implements ValueObject, Comparable<Designation>{
    
    private String designation;

    public Designation(String designation) {
        Preconditions.noneNull(designation);
        
        this.designation = designation;
        
    }
    
    protected Designation(){
        //ORM only
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Designation)) {
            return false;
        }

        final Designation that = (Designation) o;
        return this.designation.equals(that.designation);
    }

    @Override
    public int hashCode() {
        return this.designation.hashCode();
    }

    @Override
    public int compareTo(final Designation o) {
        return designation.compareTo(o.designation);
    }

    public String toString(){
        return String.format("%s", designation);
    }
    
}
