package eapli.base.taskmanagement.domain;

import eapli.base.servicemanagement.domain.Form;
import eapli.framework.validations.Preconditions;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Raúl Coelho
 */
@Entity
public class ApprovalTask extends Task {

    @ManyToOne(cascade = CascadeType.ALL)
    private Form form;

    //true for Hierarchy Collaborator approval, false
    private TypeCollaboratorApproval collaboratorForApproval;

    public ApprovalTask(TaskType taskType, int index, Form form, TypeCollaboratorApproval collaboratorApproval) {
        super(taskType, index);
        Preconditions.noneNull(form, collaboratorApproval);
        this.form = form;
        this.collaboratorForApproval = collaboratorApproval;
    }

    protected ApprovalTask() {
        //ORM only
    }

    public TypeCollaboratorApproval collaboratorForApproval(){
        return this.collaboratorForApproval;
    }
    
     public Form forms(){
        return this.form;
    }

    @Override
    public String toString(){
        return String.format("%d , Approval Task", super.toString());
    }

}
