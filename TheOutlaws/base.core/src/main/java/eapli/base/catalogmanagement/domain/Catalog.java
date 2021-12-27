/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.catalogmanagement.domain;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.criticitymanagement.domain.Criticity;
import eapli.base.servicemanagement.domain.Service;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raulcoelho
 */
@Entity
public class Catalog implements AggregateRoot<Identifier>{


    @Id
    private Identifier identifier;

    @AttributeOverride(name = "value", column = @Column(name = "briefDescription"))
    private Description briefDescription;

    @AttributeOverride(name = "value", column = @Column(name = "completeDescription"))
    private Description completeDescription;
    
    private Title title;
    
    private Icone icone;

    @OneToOne(cascade=CascadeType.ALL)
    private Criteria criteria;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "catalog_id")
    private List<Service> services;
    
    @ManyToOne(optional = false)
    private Collaborator collaborator;
    
    @ManyToOne(optional = true)
    private Criticity criticity;
        
    public Catalog(Identifier identifier, Description briefDescription, Description completeDescription,
            Title title, Icone icone, Criteria criteria, Collaborator collaborator){
        Preconditions.ensure(  briefDescription.length()<40 && completeDescription.length()<100);
        this.identifier=identifier;
        this.briefDescription=briefDescription;
        this.completeDescription=completeDescription;
        this.title=title;
        this.icone=icone;
        this.criteria=criteria;
        this.collaborator=collaborator;
        this.services=new ArrayList<>();
    }
    
    protected Catalog(){
        //ORM only
    }
    
    public List<Service> services(){
        return services;
    }
    
    public Title title(){
        return title;
    }

    public Description briefDescription(){
        return briefDescription;
    }
    
    public Criteria criteria(){
        return criteria;
    }

    public Collaborator collaborator(){
        return collaborator;
    }
    
    public Catalog changeCriticality(Criticity criticity){
        this.criticity = criticity;
        return this;
    }
    
     public Criticity Criticality(){
        return criticity;
    }

    @Override
    public boolean sameAs(Object other) {
         if (!(other instanceof Catalog)) {
            return false;
        }

        final Catalog that = (Catalog) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public Identifier identity() {
        return this.identifier;
    }  
    
    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }
    
    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }
    
    @Override
    public String toString(){
        return String.format("Brief Description:%s, Complete Description:%s, Title:%s, Icone:%s",
                briefDescription,completeDescription,title,icone);
    }

}
