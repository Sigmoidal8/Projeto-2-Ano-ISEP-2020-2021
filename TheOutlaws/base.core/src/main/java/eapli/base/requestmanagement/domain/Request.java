/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.requestmanagement.domain;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.criticitymanagement.domain.Criticity;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.Type;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.base.taskmanagement.domain.TaskType;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author José Forno
 */
@Entity
public class Request implements AggregateRoot<UniqueCode> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    private UniqueCode uniqueCode;

    @AttributeOverride(name = "requestDate", column = @Column(name = "dateOfRequest"))
    private RequestDate dateOfRequest;

    @AttributeOverride(name = "requestDate", column = @Column(name = "pretendedDeadline"))
    private RequestDate pretendedDeadline;

    @AttributeOverride(name = "requestDate", column = @Column(name = "conclusionDate"))
    private RequestDate conclusionDate;

    private Type type;

    private Priority priority;

    @Column(name = "Feedback")
    private Feedback feedback;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    private List<String> formResponse;

    @ElementCollection
    private List<String> files;

    private RequestStatus status;

    @ManyToOne
    private Service service;

    @ManyToOne
    private Collaborator collaboratorRequest;

    private int isDraft;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TaskExecution> taskExecutions;

    public Request(UniqueCode uniqueCode, Service service, Collaborator collaboratorRequest,
            RequestDate dateOfRequest, Type type, Priority priority, RequestDate pretendedDeadline,
            List<String> files, RequestStatus status, int isDraft, List<String> formResponse, List<TaskExecution> taskExecutions) {

        Preconditions.noneNull(uniqueCode, service, collaboratorRequest, dateOfRequest, type, priority, pretendedDeadline, status, formResponse);

        this.uniqueCode = uniqueCode;
        this.service = service;
        this.collaboratorRequest = collaboratorRequest;
        this.dateOfRequest = dateOfRequest;
        this.type = type;
        this.priority = priority;
        this.pretendedDeadline = pretendedDeadline;
        this.files = files;
        this.status = status;
        this.isDraft = isDraft;
        this.formResponse = formResponse;
        this.taskExecutions = taskExecutions;

    }

    protected Request() {
        //ORM only
    }

    public boolean isDraft() {
        if (isDraft == 1) {
            return true;
        } else {
            return false;
        }
    }

    public TaskExecution approvalTask() {
        TaskExecution task = null;
            if (taskExecutions.get(0).task().type().equals(TaskType.Approval)) {
                task = taskExecutions.get(0);
            }
        return task;
    }
    
    public TaskExecution resolutionTask() {
        if(approvalTask() == null){
            return taskExecutions.get(0);
        }else{
            return taskExecutions.get(1);
        }
    }

    public UniqueCode uniqueCode() {
        return uniqueCode;
    }

    public RequestDate dateOfRequestt() {
        return dateOfRequest;
    }

    public Type type() {
        return type;
    }

    public RequestDate deadLineDate() {
        return pretendedDeadline;
    }

    public List<String> formResponse() {
        return formResponse;
    }

    public Priority priority() {
        return priority;
    }

    public Service service() {
        return service;
    }

    public Collaborator collaborator() {
        return collaboratorRequest;
    }

    public RequestStatus status() {
        return status;
    }

    public List<TaskExecution> taskExecutions() {
        return taskExecutions;
    }

    public RequestDate conclusionDate() {
        return conclusionDate;
    }

    public Request changeStatus(RequestStatus status) {
        this.status = status;
        return this;
    }

    public Request changeConclusionDate(RequestDate conclusionDate) {
        this.conclusionDate = conclusionDate;
        return this;
    }

    public Request giveFeedback(Feedback feedback) {
        this.feedback = feedback;
        return this;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Request)) {
            return false;
        }

        final Request that = (Request) other;
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
        return String.format("%s, %s", uniqueCode, service);
    }

}
