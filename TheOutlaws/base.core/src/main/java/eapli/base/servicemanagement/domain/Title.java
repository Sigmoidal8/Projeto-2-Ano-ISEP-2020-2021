/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.servicemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import javax.persistence.Embeddable;

/**
 *
 * @author Utilizador
 */

@Embeddable
public class Title implements ValueObject, Comparable<Title>{
    
    private String title;
    
    public Title (String title){
        Preconditions.noneNull(title);
        Preconditions.ensure(title.length()<21);
        
        this.title = title;
    }
    
    protected Title(){
        //ORM only
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Title)) {
            return false;
        }

        final Title that = (Title) o;
        return this.title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return this.title.hashCode();
    }

    @Override
    public int compareTo(final Title o) {
        return title.compareTo(o.title);
    }

    public String toString(){
        return String.format("%s", title);
    }
    
}
