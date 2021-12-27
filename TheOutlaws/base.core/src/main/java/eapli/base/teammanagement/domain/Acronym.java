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
public class Acronym implements ValueObject, Comparable<Acronym>{
    
    private String acronym;

    public Acronym(String acronym) {
        Preconditions.noneNull(acronym);
        
        this.acronym = acronym;
        
    }
    
    protected Acronym(){
        //ORM only
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Acronym)) {
            return false;
        }

        final Acronym that = (Acronym) o;
        return this.acronym.equals(that.acronym);
    }

    @Override
    public int hashCode() {
        return this.acronym.hashCode();
    }

    @Override
    public int compareTo(final Acronym o) {
        return acronym.compareTo(o.acronym);
    }

    public String toString(){
        return String.format("%s", acronym);
    }
    
}
