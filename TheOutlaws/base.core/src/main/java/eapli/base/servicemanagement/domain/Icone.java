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
public class Icone implements ValueObject /*,Comparable<Icon>*/{
    
    private String icon ;
    
    public Icone (String icon){
        
        this.icon = icon;
    }
    
    protected Icone(){
        //ORM only
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Icone)) {
            return false;
        }

        final Icone that = (Icone) o;
        return this.icon.equals(that.icon);
    }

    @Override
    public int hashCode() {
        return this.icon.hashCode();
    }
    /*
    @Override
    public int compareTo(final Icon o) {
        return icon.compareTo(o.icon);
    }
    */
    public String toString(){
        return String.format("%s", icon);
    }
    
}
