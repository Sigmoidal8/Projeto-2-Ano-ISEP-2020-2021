/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.teammanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.validations.Preconditions;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author raulcoelho
 */
@Entity
public class TeamType implements AggregateRoot<UniqueCode>{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private UniqueCode code;
    
    private Description description;
    
    private Color color;
    
    public TeamType(UniqueCode code, Description description, Color color){
        Preconditions.noneNull(code,description,color);
        Preconditions.ensure(description.length()<50);

        this.code=code;
        this.description=description;
        this.color=color;
    }

    protected TeamType(){
          //ORM only
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof TeamType)) {
            return false;
        }

        final TeamType that = (TeamType) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && color.equals(that.color);
    }

    @Override
    public UniqueCode identity() {
        return this.code;
    }
    
    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    public String toString(){
          return String.format("%s %s", description,color);
    }
    
}
