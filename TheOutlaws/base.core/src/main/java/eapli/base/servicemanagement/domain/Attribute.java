/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.servicemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Description;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Utilizador
 */
@Entity
public class Attribute implements ValueObject {
    
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Label label;
    
    private Name name;
    
    private Description description;
    
    private DataType dataType;
    
    private RegularExpression regularExpression;
    
    public Attribute(Label label, Name name, Description description,
            DataType dataType, RegularExpression regularExpression){
        this.label=label;
        this.name=name;
        this.description=description;
        this.dataType=dataType;
        this.regularExpression=regularExpression;
    }


    protected Attribute(){
        //ORM only
    }
    
    public Name name(){
        return this.name;
    }
    
    public Description description(){
        return this.description;
    }
    
    public RegularExpression regularExpression(){
        return this.regularExpression;
    }

    public  DataType dataType(){
        return  this.dataType;
    }
    
    public String toString(){
        return String.format("%s", name);
    }
}
