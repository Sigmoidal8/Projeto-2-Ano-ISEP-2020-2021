/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.teammanagement.domain;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 *
 * @author miguel
 */
@Entity
public class Team implements AggregateRoot<UniqueCode>{
    
    private static final long serialVersionUID = 1L;
    
    @Version
    private Long version;
    
    
    @Id
    private UniqueCode uniqueCode;
    
    private Acronym acronym;
    
    private Designation designation;
    
    @ManyToOne(optional = false)
    private TeamType teamType;
    
    @ManyToMany()
    @JoinTable(name="TEAM_COLLABORATORRESPONSIBLE")
    private List<Collaborator> collaboratorsResponsible;
    
    
    @ManyToMany()
    @JoinTable(name="TEAM_COLLABORATOR")
    private List<Collaborator> collaboratorsMembers;
    
    
    public Team(UniqueCode uniqueCode, Acronym acronym, Designation designation,TeamType teamType, List<Collaborator> collaboratorsResponsible){//collaborators
        
        Preconditions.noneNull(uniqueCode,acronym,designation);
        
        this.uniqueCode=uniqueCode;
        this.acronym=acronym;
        this.designation=designation;
        this.teamType=teamType;
        this.collaboratorsResponsible=collaboratorsResponsible;
        this.collaboratorsMembers=new ArrayList<Collaborator>();
    }
    
    
    protected Team(){
        //ORM only
    }
    
    public List<Collaborator> collaborators(){
        return collaboratorsMembers;
    }
    
    public List<Collaborator> collaboratorsResponsible(){
        return collaboratorsResponsible;
    }
    
    public TeamType teamType(){
        return teamType;
    }

    @Override
    public boolean sameAs(Object other) {
         if (!(other instanceof Team)) {
            return false;
        }

        final Team that = (Team) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }    

    @Override
    public UniqueCode identity() {
        return this.uniqueCode;
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
        return String.format("%s, %s, %s",uniqueCode,acronym,designation);
    }
}
