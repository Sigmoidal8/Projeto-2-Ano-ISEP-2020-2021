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
public class CompleteDescription implements ValueObject /*,Comparable<CompleteDescription>*/{
    
    private String completeDescription;
    
    public CompleteDescription (String briefDescription){
        Preconditions.ensure(briefDescription.length()<101);
        
        this.completeDescription = briefDescription;
    }
    
    protected CompleteDescription(){
        //ORM only
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompleteDescription)) {
            return false;
        }

        final CompleteDescription that = (CompleteDescription) o;
        return this.completeDescription.equals(that.completeDescription);
    }

    @Override
    public int hashCode() {
        return this.completeDescription.hashCode();
    }
    /*
    @Override
    public int compareTo(final CompleteDescription o) {
        return completeDescription.compareTo(o.completeDescription);
    }
    */
    public String toString(){
        return String.format("%s", completeDescription);
    }
    
}
