package eapli.base.app.backoffice.console.presentation.tasklibrary;

import eapli.base.app.backoffice.console.presentation.services.SpecifyServiceUI;
import eapli.base.languagemanagement.application.FormValidator;
import eapli.base.languagemanagement.application.TaskValidator;
import eapli.base.servicemanagement.application.SpecifyServiceController;
import eapli.base.servicemanagement.domain.Form;
import eapli.base.servicemanagement.domain.Script;
import eapli.base.taskmanagement.domain.Task;
import eapli.base.taskmanagement.domain.TaskType;
import eapli.base.taskmanagement.domain.TypeCollaboratorApproval;
import eapli.base.workflowmanagement.domain.Workflow;
import eapli.base.workflowmanagement.domain.WorkflowStatus;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Date;

/**
 * @author Raúl Coelho
 */
public class AutomaticTaskRepresentation extends AbstractUI {

    private SpecifyServiceController theController;
    private SpecifyServiceUI  theUI;

    public AutomaticTaskRepresentation(SpecifyServiceController theController, SpecifyServiceUI theUI){
        this.theController=theController;
        this.theUI=theUI;
    }

    @Override
    protected boolean doShow() {
        Date startDate = new Date();
        WorkflowStatus status = WorkflowStatus.ACTIVE;
        Workflow workflow = theController.createWorkflow(startDate, status);
        boolean approval = Console.readBoolean("It needs an approval task?(y or n)");
        if (approval) {
            System.out.println("Approval task:");
            TaskType type = TaskType.Approval;
            TypeCollaboratorApproval collaboratorApproval = null;
            int option = 1;
            while (option != -1) {
                option = Console.readInteger("Who do you want to approve the request?\n" +
                        "1-The responsible hierarchical collaborator\n2-The collaborator responsible for the catalog");

                switch (option) {
                    case 1:
                        collaboratorApproval = TypeCollaboratorApproval.HierarchyCollaborator;
                        option = -1;
                        break;
                    case 2:
                        collaboratorApproval = TypeCollaboratorApproval.CatalogResponsibleCollaborator;
                        option = -1;
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }
            System.out.println("Approval Task Form Data:");
            Form form = theUI.askForForms();
            Task theApprovalTask = theController.createApprovalTask(type, 0, form, collaboratorApproval);
            workflow.tasks().add(theApprovalTask);
        }
        System.out.println("Add a task:");
        TaskType taskRealizationType = TaskType.Realization;

        Script script;
        do {
            String scriptLocation = Console.readLine("Location of the script of the task:");
            script = theController.createScript(scriptLocation);
        } while (TaskValidator.validateScript(script) == false);
        Task theRealizationTask = theController.createAutomaticTask(taskRealizationType, script,1);
        workflow.tasks().add(theRealizationTask);
        theController.specifyWorkflow(workflow);
        return true;
    }

    @Override
    public String headline() {
        return "Automatic Service Specification";
    }
}
