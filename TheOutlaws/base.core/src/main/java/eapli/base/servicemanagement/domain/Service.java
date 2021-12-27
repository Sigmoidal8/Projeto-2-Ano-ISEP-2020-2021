/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.servicemanagement.domain;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.criticitymanagement.domain.Criticity;
import eapli.base.workflowmanagement.domain.Workflow;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Utilizador
 */
@Entity
public class Service implements AggregateRoot<UniqueCode> {

    private static final long serialVersionUID = 1L;

    @Id
    private UniqueCode uniqueCode;

    private Title title;

    private BriefDescription briefDescription;

    private CompleteDescription completeDescription;

    private Type type;

    @ElementCollection
    private List<Keyword> keywords;

    private Icone icone;

    @OneToOne(cascade = CascadeType.ALL)
    private Form form;

    private int isDraft;

    @ManyToOne
    private Collaborator collaborator;

    @OneToOne(cascade = CascadeType.ALL)
    private Workflow workflows;

    @ManyToOne(optional = true)
    private Criticity criticity;

    public Service(UniqueCode uniqueCode, Title title, BriefDescription briefDescription,
            CompleteDescription completeDescription, Type type, List<Keyword> keywords,
            Icone icon, Form form, int isDraft, Collaborator collaborator, Workflow workflows) {
        if (isDraft == 0) {
            Preconditions.noneNull(uniqueCode, title, briefDescription, completeDescription,
                    type, keywords, icon, form, collaborator, workflows);
        }

        this.uniqueCode = uniqueCode;
        this.title = title;
        this.briefDescription = briefDescription;
        this.completeDescription = completeDescription;
        this.type = type;
        this.keywords = keywords;
        this.icone = icon;
        this.form = form;
        this.isDraft = isDraft;
        this.collaborator = collaborator;
        this.workflows = workflows;
    }

    protected Service() {
        //ORM only
    }

    public UniqueCode uniqueCode() {
        return this.uniqueCode;
    }

    public Title title() {
        return title;
    }

    public BriefDescription briefDescription() {
        return briefDescription;
    }

    public CompleteDescription completeDescription() {
        return completeDescription;
    }

    public Type type() {
        return type;
    }

    public List<Keyword> keywords() {
        return keywords;
    }

    public Icone icone() {
        return icone;
    }

    public Service changeCriticality(Criticity criticity) {
        this.criticity = criticity;
        return this;
    }

    public Form forms() {
        return form;
    }

    public Criticity criticality() {
        return this.criticity;
    }
    
    public Workflow workflows() {
        return workflows;
    }

    public boolean isDraft() {
        if (isDraft == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Collaborator collaboratorWhoDefined() {
        return collaborator;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Service)) {
            return false;
        }

        final Service that = (Service) other;
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
    public String toString() {
        return String.format("%s, %s", uniqueCode, title);
    }
}
