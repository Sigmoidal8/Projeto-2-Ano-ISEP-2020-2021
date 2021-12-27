/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.collaboratormanagement.domain;

import eapli.base.taskmanagement.domain.Task;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.validations.Preconditions;
import java.util.Date;

import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author raulcoelho
 */
@Entity
public class Collaborator implements AggregateRoot<MecanographicNumber> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    private MecanographicNumber mechanographicNumber;

    private ShortName shortName;

    private CompleteName completeName;

    private EmailAddress email;

    private MobileNumber mobileNumber;

    private BirthDate birthDate;

    private Address address;

    @ElementCollection
    private List<Role> role;
    
    @OneToOne(optional = true)
    private Collaborator collaborator;
    
    @OneToMany
    private List<TaskExecution> task;

    public Collaborator(MecanographicNumber mechanographicNumber, ShortName shortName, CompleteName completeName, EmailAddress email, MobileNumber mobileNumber, BirthDate birthDate, Address address, List<Role> roles, Collaborator colab) {
        Preconditions.noneNull(mechanographicNumber,shortName,completeName,email,mobileNumber,birthDate,address);
        //Preconditions.ensure(shortName.length() <= 30 && completeName.length() <= 80);
        this.mechanographicNumber = mechanographicNumber;
        this.shortName = shortName;
        this.completeName = completeName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.birthDate = birthDate;
        this.address = address;
        if (roles != null) {
            this.role = roles;
        }
        if (colab != null) {
            this.collaborator = colab;
        }
    }

    protected Collaborator() {
        //ORM only
    }

    public Collaborator collaboratorResponsible(){
        return collaborator;
    }
    public CompleteName completeName(){
        return completeName;
    }
    public EmailAddress email(){
        return email;
    }
    
    public List<TaskExecution> tasks(){
        return task;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Collaborator)) {
            return false;
        }

        final Collaborator that = (Collaborator) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

     @Override
    public MecanographicNumber identity() {
        return mechanographicNumber;
    }
    
    @Override
    public String toString(){
        return String.format("%s,%s,%s,%s,%s,%s,%s", mechanographicNumber, shortName, completeName, email, mobileNumber, birthDate, address);
    }
}
