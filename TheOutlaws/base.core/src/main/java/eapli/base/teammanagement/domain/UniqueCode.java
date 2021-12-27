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
public class UniqueCode implements ValueObject, Comparable<UniqueCode> {
    
    private static final long serialVersionUID = 1L;

    private String uniqueCode;

    public UniqueCode(String uniqueCode) {
        Preconditions.noneNull(uniqueCode);
        Preconditions.ensure(uniqueCode.length() < 16);
        
        this.uniqueCode = uniqueCode;
        
    }
    
    protected UniqueCode(){
        //ORM only
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UniqueCode)) {
            return false;
        }

        final UniqueCode that = (UniqueCode) o;
        return this.uniqueCode.equals(that.uniqueCode);
    }

    @Override
    public int hashCode() {
        return this.uniqueCode.hashCode();
    }

    @Override
    public int compareTo(final UniqueCode o) {
        return uniqueCode.compareTo(o.uniqueCode);
    }

    public String toString(){
        return String.format("%s", uniqueCode);
    }

}
