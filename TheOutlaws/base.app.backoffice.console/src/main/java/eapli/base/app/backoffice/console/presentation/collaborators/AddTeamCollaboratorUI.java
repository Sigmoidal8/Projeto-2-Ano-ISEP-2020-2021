/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.collaborators;

import eapli.base.app.backoffice.console.presentation.teams.TeamPrinter;
import eapli.base.collaboratormanagement.aplication.AddTeamCollaboratorController;
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
public class AddTeamCollaboratorUI extends AbstractUI {

    private final AddTeamCollaboratorController theController = new AddTeamCollaboratorController();

    @Override
    protected boolean doShow() {

        //Select the team
        final Iterable<Team> teams = this.theController.teams();

        final SelectWidget<Team> selector = new SelectWidget<>("Teams:", teams, new TeamPrinter());
        selector.show();

        final Team theTeam = selector.selectedElement();

        //seleciona o collaborador
        final Iterable<Collaborator> collaboratorsAux = this.theController.collaborators();
        List<Collaborator> colabs = new ArrayList<Collaborator>();
        collaboratorsAux.forEach(colabs::add);

        List<Collaborator> colabsSelected = new ArrayList<Collaborator>();

        do {
            boolean verify = false;
            final SelectWidget<Collaborator> selector1 = new SelectWidget<>("Teams:", colabs, new CollaboratorPrinter());
            selector1.show();
            final Collaborator theCollaborator = selector1.selectedElement();

            final Iterable<Team> colabTeams = verifyCollaboratorTeams(theCollaborator);
            for (Team t : colabTeams) {
                if (t.teamType().equals(theTeam.teamType())) {
                    verify = true;
                    System.out.println("This collaborator already has a team with this team type");
                }
            }
            colabs.remove(colabsSelected);
            if (!verify) {
                colabsSelected.add(theCollaborator);
            }
        } while (Console.readInteger("Want to Select a Collaborator?\n1 to continue;0 to stop") != 0);

        try {
            this.theController.addTeamCollaborator(theTeam, colabsSelected);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Add Collaborrator Failed.");
        }
        return false;
    }

    private Iterable<Team> verifyCollaboratorTeams(Collaborator colab) {
        return theController.verifyCollaboratorTeams(colab);
    }

    @Override
    public String headline() {
        return "Add Team Collaborator";
    }

}
