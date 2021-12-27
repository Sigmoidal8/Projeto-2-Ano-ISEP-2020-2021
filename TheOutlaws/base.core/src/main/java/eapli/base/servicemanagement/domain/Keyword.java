/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.servicemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.List;
import javax.persistence.Embeddable;

/**
 *
 * @author Utilizador
 */
@Embeddable
public class Keyword implements ValueObject/*, Comparable<Keyword>*/{
    
    private String keyword;
    
    public Keyword (String keyword){
        
        this.keyword = keyword;
    }
    
    protected Keyword(){
        //ORM only
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Keyword)) {
            return false;
        }

        final Keyword that = (Keyword) o;
        return this.keyword.equals(that.keyword);
    }

    @Override
    public int hashCode() {
        return this.keyword.hashCode();
    }
    /*
    @Override
    public int compareTo(final Keyword o) {
        return keyword.compareTo(o.keyword);
    }
    */
    public String toString(){
        return String.format("%s", keyword);
    }
    
}
