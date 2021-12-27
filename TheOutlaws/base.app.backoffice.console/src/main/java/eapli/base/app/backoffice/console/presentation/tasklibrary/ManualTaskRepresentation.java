package eapli.base.app.backoffice.console.presentation.tasklibrary;

import eapli.base.app.backoffice.console.presentation.collaborators.CollaboratorPrinter;
import eapli.base.app.backoffice.console.presentation.services.SpecifyServiceUI;
import eapli.base.app.backoffice.console.presentation.teams.TeamPrinter;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.servicemanagement.application.SpecifyServiceController;
import eapli.base.servicemanagement.domain.Form;
import eapli.base.taskmanagement.domain.Task;
import eapli.base.taskmanagement.domain.TaskType;
import eapli.base.taskmanagement.domain.TypeCollaboratorApproval;
import eapli.base.teammanagement.domain.Team;
import eapli.base.workflowmanagement.domain.Workflow;
import eapli.base.workflowmanagement.domain.WorkflowStatus;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Raúl Coelho
 */
public class ManualTaskRepresentation extends AbstractUI {

    private SpecifyServiceController theController;
    private SpecifyServiceUI theUI;

    public ManualTaskRepresentation(SpecifyServiceController theController, SpecifyServiceUI theUI) {
        this.theController = theController;
        this.theUI = theUI;
    }

    @Override
    protected boolean doShow() {
        Date startDate = new Date();
        WorkflowStatus status = WorkflowStatus.ACTIVE;
        Workflow workflow = theController.createWorkflow(startDate, status);
        boolean approval = Console.readBoolean("It needs an approval task?(y or n)");
        if (approval) {
            System.out.println("Approval task");
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

        System.out.println("Add a task");
        TaskType taskRealizationType = TaskType.Realization;
        int option = 1;
        List<Team> chosenTeams = new ArrayList<>();
        List<Collaborator> chosenCollaborators = new ArrayList<>();
        while (option != 0) {
            option = Console.readInteger("Which type you want to insert so that the task can be done in the future?\n1-Team\n2-Collaborator");
            switch (option) {
                case 1:
                    final Iterable<Team> teams = this.theController.teams();
                    chosenTeams = new ArrayList<>();
                    List<Team> teamsAux = new ArrayList<>();
                    teams.forEach(teamsAux::add);
                    Team theTeam = null;

                    while(chosenTeams.isEmpty()) {
                        while (Console.readInteger("Do you want to add a team?\n1-Yes\n0-No") != 0) {
                            final SelectWidget<Team> selector = new SelectWidget<>("Teams:", teamsAux, new TeamPrinter());
                            selector.show();
                            theTeam = selector.selectedElement();
                            teamsAux.remove(theTeam);
                            chosenTeams.add(theTeam);
                        }
                    }
                    option = 0;
                    break;
                case 2:
                    final Iterable<Collaborator> collaborators = this.theController.collaborators();
                    chosenCollaborators = new ArrayList<>();
                    List<Collaborator> collaboratorsAux = new ArrayList<>();
                    collaborators.forEach(collaboratorsAux::add);

                    Collaborator theCollaborator = null;

                    while(chosenCollaborators.isEmpty()) {
                        while (Console.readInteger("Do you want to add a collaborator?\n1-Yes\n0-No") != 0) {
                            final SelectWidget<Collaborator> selector = new SelectWidget<>("Collaborators:", collaboratorsAux, new CollaboratorPrinter());
                            selector.show();
                            theCollaborator = selector.selectedElement();
                            collaboratorsAux.remove(theCollaborator);
                            chosenCollaborators.add(theCollaborator);
                        }
                    }

                    option = 0;
                    break;
                default:
                    break;
            }
        }
        if (chosenCollaborators.isEmpty()) {
            if (chosenTeams.isEmpty()) {
                System.out.println("You didn't choose neither a team nor a collaborator");
            } else {
                System.out.println("Manual Task Form Data:");
                Form form = theUI.askForForms();
                Task theRealizationTask = theController.createManualTask(taskRealizationType, form, 1, chosenTeams);
                workflow.tasks().add(theRealizationTask);
            }
        } else {
            System.out.println("Manual Task Form Data:");
            Form form = theUI.askForForms();
            Task theRealizationTask = theController.createManualTask(taskRealizationType, form, chosenCollaborators, 1);
            workflow.tasks().add(theRealizationTask);
        }
        theController.specifyWorkflow(workflow);
        return true;
    }

    @Override
    public String headline() {
        return "Manual Service Specification";
    }
}
