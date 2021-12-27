/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.requestmanagement.domain;

import eapli.base.collaboratormanagement.domain.Address;
import eapli.base.collaboratormanagement.domain.BirthDate;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.domain.CompleteName;
import eapli.base.collaboratormanagement.domain.MecanographicNumber;
import eapli.base.collaboratormanagement.domain.MobileNumber;
import eapli.base.collaboratormanagement.domain.ShortName;
import eapli.base.servicemanagement.domain.Attribute;
import eapli.base.servicemanagement.domain.BriefDescription;
import eapli.base.servicemanagement.domain.CompleteDescription;
import eapli.base.servicemanagement.domain.DataType;
import eapli.base.servicemanagement.domain.Form;
import eapli.base.servicemanagement.domain.Icone;
import eapli.base.servicemanagement.domain.Keyword;
import eapli.base.servicemanagement.domain.Label;
import eapli.base.servicemanagement.domain.Name;
import eapli.base.servicemanagement.domain.RegularExpression;
import eapli.base.servicemanagement.domain.Script;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.Title;
import eapli.base.servicemanagement.domain.Type;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.base.workflowmanagement.domain.Workflow;
import eapli.base.workflowmanagement.domain.WorkflowStatus;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.base.servicemanagement.domain.UniqueCode;
import eapli.base.taskmanagement.domain.Task;
import eapli.base.taskmanagement.domain.TaskStatus;
import eapli.base.taskmanagement.domain.TaskType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Forno
 */
public class RequestTest {
    
    private final Keyword key = new Keyword("key");
    private final Form form = new Form();
    private final Collaborator colab = new Collaborator(new MecanographicNumber(234), new ShortName("shortName"), new CompleteName("completeName"), EmailAddress.valueOf("email@gmail.com"), new MobileNumber("99999999"), new BirthDate(new Date(10, 11, 2020)), new Address("Portugal", "Lisboa", "Almada", "Rua da esquina"),new ArrayList<Role>(), null);
    
    
    
@Test(expected = IllegalArgumentException.class)
    public void ensureTeamCantHaveNullUniqueCode() {
        List<Attribute> attributes = new ArrayList();
        Attribute attribute = new Attribute( new Label("label"), new Name("name"),Description.valueOf("descricao"),DataType.String, new RegularExpression("regularExpression"));
        attributes.add(attribute);
    Script script = new Script("src/test/resources/scripts/script1.txt");
        Form form = new Form(new Name("Name"), script, attributes);
        WorkflowStatus status;
        
        Workflow work = new Workflow(new Date(),WorkflowStatus.ACTIVE);
        
        List<Keyword> keywords=new ArrayList<>();
        keywords.add(key);
        
        Service serv = new Service(new eapli.base.servicemanagement.domain.UniqueCode("Code"), new Title("Code"),new BriefDescription("coisa"),new CompleteDescription("descricao"), Type.Automatic,keywords,new Icone("Icone"), form,1,colab,work);
        
        
        System.out.println("Ensure Request  Cant Have Null Unique Code");
        List<Collaborator> colabList = new ArrayList<>();
        colabList.add(colab);
        
        List<String> files = new ArrayList<>();
        List<String> response = new ArrayList<>();
        
        Task t = new Task(TaskType.Approval,1);
        TaskExecution te = new TaskExecution(t,TaskStatus.active);
        
        List<TaskExecution> teL = new ArrayList<>();
        teL.add(te);
        
        Request instance = new Request(null,serv,colab,new RequestDate(new Date()), Type.Automatic, Priority.Moderate, new RequestDate(new Date()),files,RequestStatus.Submited,0,response,teL);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureTeamCantHaveNullService() {
        List<Attribute> attributes = new ArrayList();
        Attribute attribute = new Attribute( new Label("label"), new Name("name"),Description.valueOf("descricao"),DataType.String, new RegularExpression("regularExpression"));
        attributes.add(attribute);
        Script script = new Script("src/test/resources/scripts/script1.txt");
        Form form = new Form(new Name("Name"), script, attributes);
        WorkflowStatus status;
        
        Workflow work = new Workflow(new Date(),WorkflowStatus.ACTIVE);
        
        List<Keyword> keywords=new ArrayList<>();
        keywords.add(key);
        
        Service serv = new Service(new eapli.base.servicemanagement.domain.UniqueCode("Code"), new Title("Code"),new BriefDescription("coisa"),new CompleteDescription("descricao"), Type.Automatic,keywords,new Icone("Icone"), form,1,colab,work);
        
        
        System.out.println("Ensure Request  Cant Have Null Service");
        List<Collaborator> colabList = new ArrayList<>();
        colabList.add(colab);
        
        List<String> files = new ArrayList<>();
        List<String> response = new ArrayList<>();
        
        Task t = new Task(TaskType.Approval,1);
        TaskExecution te = new TaskExecution(t,TaskStatus.active);
        
        List<TaskExecution> teL = new ArrayList<>();
        teL.add(te);
        
        Request instance = new Request(new eapli.base.requestmanagement.domain.UniqueCode("Code"),null,colab,new RequestDate(new Date()), Type.Automatic, Priority.Moderate, new RequestDate(new Date()),files,RequestStatus.Submited,0,response,teL);
    }
    
     @Test(expected = IllegalArgumentException.class)
    public void ensureTeamCantHaveNullCollaborator() {
        List<Attribute> attributes = new ArrayList();
        Attribute attribute = new Attribute( new Label("label"), new Name("name"),Description.valueOf("descricao"),DataType.String, new RegularExpression("regularExpression"));
        attributes.add(attribute);
         Script script = new Script("src/test/resources/scripts/script1.txt");
        Form form = new Form(new Name("Name"), script, attributes);
        WorkflowStatus status;
        
        Workflow work = new Workflow(new Date(),WorkflowStatus.ACTIVE);
        
        List<Keyword> keywords=new ArrayList<>();
        keywords.add(key);
        
        Service serv = new Service(new eapli.base.servicemanagement.domain.UniqueCode("Code"), new Title("Code"),new BriefDescription("coisa"),new CompleteDescription("descricao"), Type.Automatic,keywords,new Icone("Icone"), form,1,colab,work);
        
        
        System.out.println("Ensure Request  Cant Have Null Collaborator");
        List<Collaborator> colabList = new ArrayList<>();
        colabList.add(colab);
        
        List<String> files = new ArrayList<>();
        List<String> response = new ArrayList<>();
        
        Task t = new Task(TaskType.Approval,1);
        TaskExecution te = new TaskExecution(t,TaskStatus.active);
        
        List<TaskExecution> teL = new ArrayList<>();
        teL.add(te);
        
        Request instance = new Request(new eapli.base.requestmanagement.domain.UniqueCode("Code"),serv,null,new RequestDate(new Date()), Type.Automatic, Priority.Moderate, new RequestDate(new Date()),files,RequestStatus.Submited,0,response,teL);
    }
    
     @Test(expected = IllegalArgumentException.class)
    public void ensureTeamCantHaveNullDateRequest() {
        List<Attribute> attributes = new ArrayList();
        Attribute attribute = new Attribute( new Label("label"), new Name("name"),Description.valueOf("descricao"),DataType.String, new RegularExpression("regularExpression"));
        attributes.add(attribute);
         Script script = new Script("src/test/resources/scripts/script1.txt");
        Form form = new Form(new Name("Name"), script, attributes);
        WorkflowStatus status;
        
        Workflow work = new Workflow(new Date(),WorkflowStatus.ACTIVE);
        
        List<Keyword> keywords=new ArrayList<>();
        keywords.add(key);
        
        Service serv = new Service(new eapli.base.servicemanagement.domain.UniqueCode("Code"), new Title("Code"),new BriefDescription("coisa"),new CompleteDescription("descricao"), Type.Automatic,keywords,new Icone("Icone"), form,1,colab,work);
        
        
        System.out.println("Ensure Request  Cant Have Null DateRequest");
        List<Collaborator> colabList = new ArrayList<>();
        colabList.add(colab);
        
        List<String> files = new ArrayList<>();
        List<String> response = new ArrayList<>();
        
        Task t = new Task(TaskType.Approval,1);
        TaskExecution te = new TaskExecution(t,TaskStatus.active);
        
        List<TaskExecution> teL = new ArrayList<>();
        teL.add(te);
        
        Request instance = new Request(new eapli.base.requestmanagement.domain.UniqueCode("Code"),serv,colab,null, Type.Automatic, Priority.Moderate, new RequestDate(new Date()),files,RequestStatus.Submited,0,response,teL);
    }
    
     @Test(expected = IllegalArgumentException.class)
    public void ensureTeamCantHaveNullType() {
        List<Attribute> attributes = new ArrayList();
        Attribute attribute = new Attribute( new Label("label"), new Name("name"),Description.valueOf("descricao"),DataType.String, new RegularExpression("regularExpression"));
        attributes.add(attribute);
         Script script = new Script("src/test/resources/scripts/script1.txt");
        Form form = new Form(new Name("Name"), script, attributes);
        WorkflowStatus status;
        
        Workflow work = new Workflow(new Date(),WorkflowStatus.ACTIVE);
        
        List<Keyword> keywords=new ArrayList<>();
        keywords.add(key);
        
        Service serv = new Service(new eapli.base.servicemanagement.domain.UniqueCode("Code"), new Title("Code"),new BriefDescription("coisa"),new CompleteDescription("descricao"), Type.Automatic,keywords,new Icone("Icone"), form,1,colab,work);
        
        
        System.out.println("Ensure Request  Cant Have Null Type");
        List<Collaborator> colabList = new ArrayList<>();
        colabList.add(colab);
        
        List<String> files = new ArrayList<>();
        List<String> response = new ArrayList<>();
        
        Task t = new Task(TaskType.Approval,1);
        TaskExecution te = new TaskExecution(t,TaskStatus.active);
        
        List<TaskExecution> teL = new ArrayList<>();
        teL.add(te);
        
        Request instance = new Request(new eapli.base.requestmanagement.domain.UniqueCode("Code"),serv,colab,new RequestDate(new Date()), null, Priority.Moderate, new RequestDate(new Date()),files,RequestStatus.Submited,0,response,teL);
    }
     @Test(expected = IllegalArgumentException.class)
    public void ensureTeamCantHaveNullPriority() {
        List<Attribute> attributes = new ArrayList();
        Attribute attribute = new Attribute( new Label("label"), new Name("name"),Description.valueOf("descricao"),DataType.String, new RegularExpression("regularExpression"));
        attributes.add(attribute);
         Script script = new Script("src/test/resources/scripts/script1.txt");
        Form form = new Form(new Name("Name"), script, attributes);
        WorkflowStatus status;
        
        Workflow work = new Workflow(new Date(),WorkflowStatus.ACTIVE);
        
        List<Keyword> keywords=new ArrayList<>();
        keywords.add(key);
        
        Service serv = new Service(new eapli.base.servicemanagement.domain.UniqueCode("Code"), new Title("Code"),new BriefDescription("coisa"),new CompleteDescription("descricao"), Type.Automatic,keywords,new Icone("Icone"), form,1,colab,work);
        
        
        System.out.println("Ensure Request  Cant Have Null Unique Code");
        List<Collaborator> colabList = new ArrayList<>();
        colabList.add(colab);
        
        List<String> files = new ArrayList<>();
        List<String> response = new ArrayList<>();
        
        Task t = new Task(TaskType.Approval,1);
        TaskExecution te = new TaskExecution(t,TaskStatus.active);
        
        List<TaskExecution> teL = new ArrayList<>();
        teL.add(te);
        
        Request instance = new Request(new eapli.base.requestmanagement.domain.UniqueCode("Code"),serv,colab,new RequestDate(new Date()), Type.Automatic, null, new RequestDate(new Date()),files,RequestStatus.Submited,0,response,teL);
    }
    
     @Test(expected = IllegalArgumentException.class)
    public void ensureTeamCantHaveNullDeadline() {
        List<Attribute> attributes = new ArrayList();
        Attribute attribute = new Attribute( new Label("label"), new Name("name"),Description.valueOf("descricao"),DataType.String, new RegularExpression("regularExpression"));
        attributes.add(attribute);
         Script script = new Script("src/test/resources/scripts/script1.txt");
        Form form = new Form(new Name("Name"), script, attributes);
        WorkflowStatus status;
        
        Workflow work = new Workflow(new Date(),WorkflowStatus.ACTIVE);
        
        List<Keyword> keywords=new ArrayList<>();
        keywords.add(key);
        
        Service serv = new Service(new eapli.base.servicemanagement.domain.UniqueCode("Code"), new Title("Code"),new BriefDescription("coisa"),new CompleteDescription("descricao"), Type.Automatic,keywords,new Icone("Icone"), form,1,colab,work);
        
        
        System.out.println("Ensure Request  Cant Have Null Deadline");
        List<Collaborator> colabList = new ArrayList<>();
        colabList.add(colab);
        
        List<String> files = new ArrayList<>();
        List<String> response = new ArrayList<>();
        
        Task t = new Task(TaskType.Approval,1);
        TaskExecution te = new TaskExecution(t,TaskStatus.active);
        
        List<TaskExecution> teL = new ArrayList<>();
        teL.add(te);
        
        Request instance = new Request(new eapli.base.requestmanagement.domain.UniqueCode("Code"),serv,colab,new RequestDate(new Date()), Type.Automatic, Priority.Moderate, null,files,RequestStatus.Submited,0,response,teL);
    }
    
     @Test(expected = IllegalArgumentException.class)
    public void ensureTeamCantHaveNullStatus() {
        List<Attribute> attributes = new ArrayList();
        Attribute attribute = new Attribute( new Label("label"), new Name("name"),Description.valueOf("descricao"),DataType.String, new RegularExpression("regularExpression"));
        attributes.add(attribute);
         Script script = new Script("src/test/resources/scripts/script1.txt");
        Form form = new Form(new Name("Name"), script, attributes);
        WorkflowStatus status;
        
        Workflow work = new Workflow(new Date(),WorkflowStatus.ACTIVE);
        
        List<Keyword> keywords=new ArrayList<>();
        keywords.add(key);
        
        Service serv = new Service(new eapli.base.servicemanagement.domain.UniqueCode("Code"), new Title("Code"),new BriefDescription("coisa"),new CompleteDescription("descricao"), Type.Automatic,keywords,new Icone("Icone"), form,1,colab,work);
        
        
        System.out.println("Ensure Request  Cant Have Null Status");
        List<Collaborator> colabList = new ArrayList<>();
        colabList.add(colab);
        
        List<String> files = new ArrayList<>();
        List<String> response = new ArrayList<>();
        
        Task t = new Task(TaskType.Approval,1);
        TaskExecution te = new TaskExecution(t,TaskStatus.active);
        
        List<TaskExecution> teL = new ArrayList<>();
        teL.add(te);
        
        Request instance = new Request(new eapli.base.requestmanagement.domain.UniqueCode("Code"),serv,colab,new RequestDate(new Date()), Type.Automatic, Priority.Moderate, new RequestDate(new Date()),files,null,0,response,teL);
    }
    
     @Test(expected = IllegalArgumentException.class)
    public void ensureTeamCantHaveNullResponse() {
        List<Attribute> attributes = new ArrayList();
        Attribute attribute = new Attribute( new Label("label"), new Name("name"),Description.valueOf("descricao"),DataType.String, new RegularExpression("regularExpression"));
        attributes.add(attribute);
         Script script = new Script("src/test/resources/scripts/script1.txt");
        Form form = new Form(new Name("Name"), script, attributes);
        WorkflowStatus status;
        
        Workflow work = new Workflow(new Date(),WorkflowStatus.ACTIVE);
        
        List<Keyword> keywords=new ArrayList<>();
        keywords.add(key);
        
        Service serv = new Service(new eapli.base.servicemanagement.domain.UniqueCode("Code"), new Title("Code"),new BriefDescription("coisa"),new CompleteDescription("descricao"), Type.Automatic,keywords,new Icone("Icone"), form,1,colab,work);
        
        
        System.out.println("Ensure Request  Cant Have Null Response");
        List<Collaborator> colabList = new ArrayList<>();
        colabList.add(colab);
        
        List<String> files = new ArrayList<>();
        List<String> response = new ArrayList<>();
        
        Task t = new Task(TaskType.Approval,1);
        TaskExecution te = new TaskExecution(t,TaskStatus.active);
        
        List<TaskExecution> teL = new ArrayList<>();
        teL.add(te);
        
        Request instance = new Request(new eapli.base.requestmanagement.domain.UniqueCode("Code"),serv,colab,new RequestDate(new Date()), Type.Automatic, Priority.Moderate, new RequestDate(new Date()),files,RequestStatus.Submited,0,null,teL);
    }
    
}
