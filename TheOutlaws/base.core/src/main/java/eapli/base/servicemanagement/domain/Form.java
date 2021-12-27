/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.servicemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.ValueObject;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Utilizador
 */
@Entity
public class Form implements AggregateRoot<Long> {
    
    private static final long serialVersionUID = 1L;
    
    @Version
    private Long version;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long uniqueCode;
    
    private Name name;
    
    private Script script;
    
    @OneToMany(cascade=CascadeType.ALL)
    private List<Attribute> attribute;
    
    public Form(Name name, Script script,
            List<Attribute>attibute){
        this.uniqueCode=uniqueCode;
        this.name=name;
        this.script=script;
        this.attribute=attibute;
    }
    
    public Form(){
        //ORM only
    }
    
    public List<Attribute> atributes(){
        return attribute;
    }

    public Script script(){
        return script;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Form)) {
            return false;
        }

        final Form that = (Form) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public Long identity() {
        return uniqueCode;
    }
}
