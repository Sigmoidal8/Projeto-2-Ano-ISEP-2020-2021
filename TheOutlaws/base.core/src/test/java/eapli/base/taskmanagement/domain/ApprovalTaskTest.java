package eapli.base.taskmanagement.domain;

import eapli.base.servicemanagement.domain.*;
import eapli.framework.general.domain.model.Description;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raúl Coelho
 */
public class ApprovalTaskTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureTaskExecutionCantHaveNullForm() {
        System.out.println("Ensure ApprovalTask Cant Have Null Form");
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute(new Label("label"), new Name("name"), Description.valueOf("description"), DataType.String, new RegularExpression("[A-Za-z]+")));
        Form form = new Form(new Name("form1"), new Script("src/test/resources/scripts/script1.txt"), attributes);


        ApprovalTask t = new ApprovalTask(TaskType.Approval, 0, null, TypeCollaboratorApproval.CatalogResponsibleCollaborator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTaskExecutionCantHaveNullCollaboratorType() {
        System.out.println("Ensure ApprovalTask Cant Have Null Collaborator Type");
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute(new Label("label"), new Name("name"), Description.valueOf("description"), DataType.String, new RegularExpression("[A-Za-z]+")));
        Form form = new Form(new Name("form1"), new Script("src/test/resources/scripts/script1.txt"), attributes);

        ApprovalTask t = new ApprovalTask(TaskType.Approval, 0, form, null);
    }
}
