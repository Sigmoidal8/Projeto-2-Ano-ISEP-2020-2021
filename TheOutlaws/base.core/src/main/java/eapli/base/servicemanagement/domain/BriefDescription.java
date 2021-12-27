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
public class BriefDescription implements ValueObject /*,Comparable<BriefDescription>*/{
    
    private String briefDescription;
    
    public BriefDescription (String briefDescription){
        Preconditions.ensure(briefDescription.length()<51);
        
        this.briefDescription = briefDescription;
    }
    
    protected BriefDescription(){
        //ORM only
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BriefDescription)) {
            return false;
        }

        final BriefDescription that = (BriefDescription) o;
        return this.briefDescription.equals(that.briefDescription);
    }

    @Override
    public int hashCode() {
        return this.briefDescription.hashCode();
    }
    /*
    @Override
    public int compareTo(final BriefDescription o) {
        return briefDescription.compareTo(o.briefDescription);
    }
    */
    public String toString(){
        return String.format("%s", briefDescription);
    }
    
}
