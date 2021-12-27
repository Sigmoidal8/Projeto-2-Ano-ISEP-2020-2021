/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.teams;

import eapli.base.app.backoffice.console.presentation.collaborators.CollaboratorPrinter;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.teammanagement.application.CreateTeamController;
import eapli.base.teammanagement.domain.TeamType;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José Forno
 */
public class CreateTeamUI extends AbstractUI {

    private final CreateTeamController theController = new CreateTeamController();

    @Override
    protected boolean doShow() {
        final Iterable<Collaborator> collaboratorsAux = this.theController.collaborators();
        List<Collaborator> collaborators = new ArrayList<>();
        collaboratorsAux.forEach(collaborators::add);
        final Iterable<TeamType> teamTypes = this.theController.teamTypes();

        final SelectWidget<TeamType> selector1 = new SelectWidget<>("Team Types:", teamTypes, new TeamTypePrinter());
        selector1.show();
        final TeamType theTeamType = selector1.selectedElement();

        List<Collaborator> collaboratorL = new ArrayList<Collaborator>();
        while (Console.readInteger("Want to Select a Collaborator?\n1 to continue;0 to stop") != 0) {
            final SelectWidget<Collaborator> selector = new SelectWidget<>("Collaborators:", collaborators,
                    new CollaboratorPrinter());
            selector.show();
            final Collaborator theCollaborator = selector.selectedElement();
            collaboratorL.add(theCollaborator);
            collaborators.remove(theCollaborator);
        }
        final String uniqueCode = Console.readLine("Unique Code");
        final String acronym = Console.readLine("Acronym");
        final String designation = Console.readLine("Designation");

        try {
            this.theController.createTeam(uniqueCode, acronym, designation, theTeamType,
                    collaboratorL);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a team which already exists in the database.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Register Team";
    }

}
