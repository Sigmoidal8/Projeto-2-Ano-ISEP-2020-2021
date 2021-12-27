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
public class RegularExpression implements ValueObject{
    
    private String regularExpression;

    public RegularExpression(String regularExpression) {
        Preconditions.noneNull(regularExpression);
        
        this.regularExpression = regularExpression;
        
    }
    
    protected RegularExpression(){
        //ORM only
    }
    
    public String toString(){
        return String.format("%s", regularExpression);
    }
    
}
