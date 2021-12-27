/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.servicemanagement.domain;

import eapli.base.collaboratormanagement.domain.*;
import eapli.base.taskmanagement.domain.ApprovalTask;
import eapli.base.taskmanagement.domain.TaskType;
import eapli.base.taskmanagement.domain.TypeCollaboratorApproval;
import eapli.base.workflowmanagement.domain.Workflow;
import eapli.base.workflowmanagement.domain.WorkflowStatus;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Role;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Utilizador
 */
public class ServiceTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureServiceCantHaveNullUniqueCode() {
        System.out.println("Ensure Service Cant Have Null Unique Code");

        List<Attribute> attributes = new ArrayList();
        List<Keyword> keywords = new ArrayList();
        keywords.add(new Keyword("key"));
        Attribute attribute = new Attribute(Label.valueOf("111"), new Name("label"), Description.valueOf("name"), DataType.String, new RegularExpression("[A-Za-z]+"));
        attributes.add(attribute);
        Script script = new Script("src/test/resources/scripts/script1.txt");
        Form form = new Form(new Name("Name"), script, attributes);
        Form form1 = new Form(new Name("Name"), script, attributes);
        Type type = Type.Automatic;
        Collaborator colab = new Collaborator(new MecanographicNumber(234), ShortName.valueOf("shortName"), CompleteName.valueOf("completeName"), EmailAddress.valueOf("email@gmail.com"), MobileNumber.valueOf("99999999"), BirthDate.valueOf(new Date(10, 11, 2020)), new Address("Portugal", "Lisboa", "Almada", "Rua da esquina"),new ArrayList<Role>(), null);
        Workflow workflow = new Workflow(new Date(), WorkflowStatus.ACTIVE);
        workflow.tasks().add(new ApprovalTask(TaskType.Approval, 0, form1, TypeCollaboratorApproval.CatalogResponsibleCollaborator));

        Service instance = new Service(null, new Title("title"), new BriefDescription("Brief"), new CompleteDescription("Complete"), Type.Manual,
                keywords, new Icone("icon"), form, 0, colab, workflow);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureServiceCantHaveNullTitle() {
        System.out.println("Ensure Service Cant Have Null Title");
        List<Attribute> attributes = new ArrayList();
        List<Keyword> keywords = new ArrayList();
        keywords.add(new Keyword("key"));
        Attribute attribute = new Attribute(Label.valueOf("111"), new Name("label"), Description.valueOf("name"), DataType.String, new RegularExpression("[A-Za-z]+"));
        attributes.add(attribute);
        Script script = new Script("src/test/resources/scripts/script1.txt");
        Form form = new Form(new Name("Name"), script, attributes);
        Form form1 = new Form(new Name("Name"), script, attributes);
        Type type = Type.Automatic;
        Collaborator colab = new Collaborator(new MecanographicNumber(234), ShortName.valueOf("shortName"), CompleteName.valueOf("completeName"), EmailAddress.valueOf("email@gmail.com"), MobileNumber.valueOf("99999999"), BirthDate.valueOf(new Date(10, 11, 2020)), new Address("Portugal", "Lisboa", "Almada", "Rua da esquina"),new ArrayList<Role>(), null);
        Workflow workflow = new Workflow(new Date(), WorkflowStatus.ACTIVE);
        workflow.tasks().add(new ApprovalTask(TaskType.Approval, 0, form1, TypeCollaboratorApproval.CatalogResponsibleCollaborator));

        Service instance = new Service(new UniqueCode("A1"), null, new BriefDescription("Brief"), new CompleteDescription("Complete"), Type.Manual,
                keywords, new Icone("icon"), form, 0, colab, workflow);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureServiceCantHaveNullCollaborator() {
        System.out.println("Ensure Service Cant Have Null Title");
        List<Attribute> attributes = new ArrayList();
        List<Keyword> keywords = new ArrayList();
        keywords.add(new Keyword("key"));
        Attribute attribute = new Attribute(Label.valueOf("111"), new Name("label"), Description.valueOf("name"), DataType.String, new RegularExpression("[A-Za-z]+"));
        attributes.add(attribute);
        Script script = new Script("src/test/resources/scripts/script1.txt");
        Form form = new Form(new Name("Name"), script, attributes);
        Form form1 = new Form(new Name("Name"), script, attributes);
        Type type = Type.Automatic;
        Collaborator colab = new Collaborator(new MecanographicNumber(234), ShortName.valueOf("shortName"), CompleteName.valueOf("completeName"), EmailAddress.valueOf("email@gmail.com"), MobileNumber.valueOf("99999999"), BirthDate.valueOf(new Date(10, 11, 2020)), new Address("Portugal", "Lisboa", "Almada", "Rua da esquina"),new ArrayList<Role>(), null);
        Workflow workflow = new Workflow(new Date(), WorkflowStatus.ACTIVE);
        workflow.tasks().add(new ApprovalTask(TaskType.Approval, 0, form1, TypeCollaboratorApproval.CatalogResponsibleCollaborator));


        Service instance = new Service(new UniqueCode("A1"), new Title("Title"), new BriefDescription("Brief"), new CompleteDescription("Complete"), Type.Manual,
                keywords, new Icone("icon"), form, 0, null, workflow);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureServiceCantHaveNullType() {
        System.out.println("Ensure Service Cant Have Null Title");
        List<Attribute> attributes = new ArrayList();
        List<Keyword> keywords = new ArrayList();
        keywords.add(new Keyword("key"));
        Attribute attribute = new Attribute(Label.valueOf("111"), new Name("label"), Description.valueOf("name"), DataType.String, new RegularExpression("[A-Za-z]+"));
        attributes.add(attribute);
        Script script = new Script("src/test/resources/scripts/script1.txt");
        Form form = new Form(new Name("Name"), script, attributes);
        Form form1 = new Form(new Name("Name"), script, attributes);
        Type type = Type.Automatic;
        Collaborator colab = new Collaborator(new MecanographicNumber(234), ShortName.valueOf("shortName"), CompleteName.valueOf("completeName"), EmailAddress.valueOf("email@gmail.com"), MobileNumber.valueOf("99999999"), BirthDate.valueOf(new Date(10, 11, 2020)), new Address("Portugal", "Lisboa", "Almada", "Rua da esquina"),new ArrayList<Role>(), null);
        Workflow workflow = new Workflow(new Date(), WorkflowStatus.ACTIVE);
        workflow.tasks().add(new ApprovalTask(TaskType.Approval, 0, form1, TypeCollaboratorApproval.CatalogResponsibleCollaborator));


        Service instance = new Service(new UniqueCode("A1"), new Title("Title"), new BriefDescription("Brief"), new CompleteDescription("Complete"), Type.Manual,
                keywords, new Icone("icon"), form, 0, colab, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureServiceCantHaveNullForm() {
        System.out.println("Ensure Service Cant Have Null Title");
        List<Attribute> attributes = new ArrayList();
        List<Keyword> keywords = new ArrayList();
        keywords.add(new Keyword("key"));
        Attribute attribute = new Attribute(Label.valueOf("111"), new Name("label"), Description.valueOf("name"), DataType.String, new RegularExpression("[A-Za-z]+"));
        attributes.add(attribute);
        Script script = new Script("src/test/resources/scripts/script1.txt");
        Form form = new Form(new Name("Name"), script, attributes);
        Form form1 = new Form(new Name("Name"), script, attributes);
        Type type = Type.Automatic;
        Collaborator colab = new Collaborator(new MecanographicNumber(234), ShortName.valueOf("shortName"), CompleteName.valueOf("completeName"), EmailAddress.valueOf("email@gmail.com"), MobileNumber.valueOf("99999999"), BirthDate.valueOf(new Date(10, 11, 2020)), new Address("Portugal", "Lisboa", "Almada", "Rua da esquina"),new ArrayList<Role>(), null);
        Workflow workflow = new Workflow(new Date(), WorkflowStatus.ACTIVE);
        workflow.tasks().add(new ApprovalTask(TaskType.Approval, 0, form1, TypeCollaboratorApproval.CatalogResponsibleCollaborator));


        Service instance = new Service(new UniqueCode("A1"), new Title("Title"), new BriefDescription("Brief"), new CompleteDescription("Complete"), Type.Manual,
                keywords, new Icone("icon"), null, 0, colab, workflow);
    }

}
