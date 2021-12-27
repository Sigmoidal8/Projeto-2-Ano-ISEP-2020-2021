/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.collaborators;

import eapli.base.app.backoffice.console.presentation.collaborators.CollaboratorPrinter;
import eapli.base.app.backoffice.console.presentation.teams.TeamPrinter;
import eapli.base.collaboratormanagement.aplication.RegisterCollaboratorController;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.teammanagement.domain.Team;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author migue
 */
public class RegisterCollaboratorUI extends AbstractUI {

    private final RegisterCollaboratorController theController = new RegisterCollaboratorController();

    @Override
    protected boolean doShow() {
        // FIXME avoid duplication with SignUpUI. reuse UserDataWidget from
        // UtenteApp
        final long mechanographicNumber = Long.parseLong(Console.readLine("Mechanographic Number:"));
        final String shortName = Console.readLine("Short Name:");
        final String completeName = Console.readLine("Complete Name:");
        final String email = Console.readLine("E-mail:");
        final String mobileNumber = Console.readLine("Mobile Number:");
        final Date birthDate = Console.readDate("Birth Date (DD/MM/YYYY):", "DD/MM/YYYY");
        System.out.println("Address:");
        final String country = Console.readLine("Country:");
        final String district = Console.readLine("District:");
        final String county = Console.readLine("County:");
        final String address = Console.readLine("Address:");

        //Seleciona as roles
        final Set<Role> roleTypes = new HashSet<>();
        boolean show;
        do {
            show = showRoles(roleTypes);
        } while (!show);

        //seleciona o collaborador
        Collaborator theCollaborator = null;
        if (Console.readInteger("Want to Select a Collaborator?\n1 to continue;0 to stop") != 0) {
            final Iterable<Collaborator> collaborators = this.theController.collaborators();

            final SelectWidget<Collaborator> selector = new SelectWidget<>("Collaborators:", collaborators, new CollaboratorPrinter());
            selector.show();
            theCollaborator = selector.selectedElement();
        }

        //seleciona a equipa
        final Iterable<Team> teamsAux = this.theController.teams();
        List<Team> teams = new ArrayList<Team>();
        teamsAux.forEach(teams::add);

        List<Team> teamsSelected = new ArrayList<Team>();

        while (Console.readInteger("Want to Select a team?\n1 to continue;0 to stop") != 0) {
            final SelectWidget<Team> selector1 = new SelectWidget<>("Teams:", teams,
                    new TeamPrinter());
            selector1.show();
            final Team theTeam = selector1.selectedElement();
            Iterator<Team> itrTeams = teams.iterator();
            
            while (itrTeams.hasNext()) {
                Team team = itrTeams.next();
                if (team.teamType() == theTeam.teamType()) {
                    itrTeams.remove();
                }
            }
            itrTeams.forEachRemaining(teams::add);
            teamsSelected.add(theTeam);
        }

        try {
            this.theController.registerCollaborator(mechanographicNumber, shortName, completeName, email, mobileNumber, birthDate, country, district, county, address, roleTypes, theCollaborator, teamsSelected);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That username is already in use.");
        } catch (IOException ex) {
            Logger.getLogger(RegisterCollaboratorUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    private boolean showRoles(final Set<Role> roleTypes) {
        // TODO we could also use the "widget" classes from the framework...
        final Menu rolesMenu = buildRolesMenu(roleTypes);
        final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildRolesMenu(final Set<Role> roleTypes) {
        final Menu rolesMenu = new Menu();
        int counter = 0;
        rolesMenu.addItem(MenuItem.of(counter++, "No Role", Actions.SUCCESS));
        for (final Role roleType : theController.getRoleTypes()) {
            rolesMenu.addItem(MenuItem.of(counter++, roleType.toString(), () -> roleTypes.add(roleType)));
        }
        return rolesMenu;
    }

    @Override
    public String headline() {
        return "Register Collaborator";
    }

}
//final Iterable<Team> teamsAux = this.theController.teams();
//        List<Team> teams = new ArrayList<Team>();
//        teamsAux.forEach(teams::add);
//
//        List<Team> teamsSelected = new ArrayList<Team>();
//
//        while (Console.readInteger("Want to Select a team?\n1 to continue;0 to stop") != 0) {
//            final SelectWidget<Team> selector1 = new SelectWidget<>("Teams:", teams,
//                    new TeamPrinter());
//            selector1.show();
//            final Team theTeam = selector1.selectedElement();
//            for (Team t : teams) {
//                if (t.teamType() == theTeam.teamType()) {
//                    teams.remove(t);
//                }
//            }