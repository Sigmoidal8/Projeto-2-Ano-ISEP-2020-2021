package eapli.base.app.backoffice.console.presentation.catalogs;

import eapli.base.app.backoffice.console.presentation.collaborators.CollaboratorPrinter;
import eapli.base.app.backoffice.console.presentation.teams.TeamPrinter;
import eapli.base.catalogmanagement.application.RegisterCatalogController;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.teammanagement.domain.Team;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raulcoelho
 */
public class RegisterCatalogUI extends AbstractUI{

    private final RegisterCatalogController theController = new RegisterCatalogController();

    @Override
    protected boolean doShow() {
        final Iterable<Collaborator> collaborators = this.theController.collaborators();
        if(!collaborators.iterator().hasNext()){
            System.out.println("Can't create a Catalog since there is no Collaborator to manage it");
            return false;
        }

        final SelectWidget<Collaborator> selector = new SelectWidget<>("Select the collaborator responsible\nCollaborators:", collaborators,
                new CollaboratorPrinter());
        selector.show();
        final Collaborator theCollaborator = selector.selectedElement();

        final Iterable<Team> teamsAux = this.theController.teams();
        if(!teamsAux.iterator().hasNext()){
            System.out.println("Can't create a Catalog since there is no Teams to access it.");
            return false;
        }
        List<Team> teams = new ArrayList<Team>();
        teamsAux.forEach(teams::add);
        List<Team> teamsSelected = new ArrayList<Team>();

        while (Console.readInteger("Want to Select another team?(you need to select at least one)\n1 to continue;0 to stop") != 0) {
            final SelectWidget<Team> selector1 = new SelectWidget<>("Teams:", teams,
                    new TeamPrinter());
            selector1.show();
            final Team theTeam = selector1.selectedElement();
            teamsSelected.add(theTeam);
            teams.remove(theTeam);
        }

        if(teamsSelected.isEmpty()){
            System.out.println("You haven't selected a team, no catalog was created");
            return false;
        }

        final Long identifier = Console.readLong("Identifier");
        final String title = Console.readLine("Title");
        final String briefDescription = Console.readLine("Brief Description");
        final String completeDescription = Console.readLine("Complete Description");
        final String icone = Console.readLine("Icone Location");

        try {
            this.theController.registerCatalog(identifier, title, briefDescription,completeDescription,
                    icone, theCollaborator, teamsSelected);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a catalog which already exists in the database.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Catalog";
    }

}

