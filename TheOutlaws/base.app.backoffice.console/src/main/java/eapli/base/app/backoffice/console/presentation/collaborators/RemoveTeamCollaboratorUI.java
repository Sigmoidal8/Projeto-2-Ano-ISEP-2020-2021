/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.collaborators;

import eapli.base.app.backoffice.console.presentation.teams.TeamPrinter;
import eapli.base.collaboratormanagement.aplication.RemoveTeamCollaboratorController;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.teammanagement.domain.Team;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author migue
 */
public class RemoveTeamCollaboratorUI extends AbstractUI {

    private final RemoveTeamCollaboratorController theController = new RemoveTeamCollaboratorController();

    @Override
    protected boolean doShow() {
        //Select the team
        final Iterable<Team> teams = this.theController.teams();

        final SelectWidget<Team> selector1 = new SelectWidget<>("Teams:", teams, new TeamPrinter());
        selector1.show();

        final Team theTeam = selector1.selectedElement();

        //select collaborator 
        do {
            final Iterable<Collaborator> collaborators = this.theController.collaborators(theTeam);

            final SelectWidget<Collaborator> selector = new SelectWidget<>("Collaborators:", collaborators, new CollaboratorPrinter());
            selector.show();

            Collaborator theCollaborator = selector.selectedElement();

            try {
                this.theController.removeTeamCollaborator(theTeam, theCollaborator);
            } catch (final IntegrityViolationException | ConcurrencyException e) {
                System.out.println("Remove Collaborrator Failed.");
            }

        } while (Console.readInteger("Want to Select another Collaborator to remove?\n1 to continue;0 to stop") != 0);

        return false;

    }

    @Override
    public String headline() {
        return "Remove Team Collaborator";
    }
}
