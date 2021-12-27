/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.backoffice.console.presentation;

import eapli.base.app.backoffice.console.presentation.dashboards.WorkflowStateUI;
import eapli.base.app.backoffice.console.presentation.services.SpecifyServiceUI;
import eapli.base.app.backoffice.console.presentation.teams.RegisterTeamTypeUI;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.Application;
import eapli.base.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.base.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.base.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.base.app.backoffice.console.presentation.catalogs.AssignCriticalityToCatalogUI;
import eapli.base.app.backoffice.console.presentation.catalogs.RegisterCatalogUI;
import eapli.base.app.backoffice.console.presentation.clientuser.AcceptRefuseSignupRequestAction;
import eapli.base.app.backoffice.console.presentation.collaborators.AddTeamCollaboratorUI;
import eapli.base.app.backoffice.console.presentation.collaborators.RegisterCollaboratorUI;
import eapli.base.app.backoffice.console.presentation.collaborators.RemoveTeamCollaboratorUI;
import eapli.base.app.backoffice.console.presentation.criticity.DefineCriticityUI;
import eapli.base.app.backoffice.console.presentation.criticity.ShowSlaUI;
import eapli.base.app.backoffice.console.presentation.teams.CreateTeamUI;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // SETTINGS
    private static final int SET_KITCHEN_ALERT_LIMIT_OPTION = 1;

    //CATALOGS
    private static final int CATALOG_REGISTER_OPTION = 1;
    private static final int CATALOG_ASSIGN_CRITICALITY_OPTION = 2;

    //COLLABORATOR
    private static final int COLLABORATOR_REGISTER_OPTION = 1;
    private static final int REMOVE_TEAM_COLLABORATOR_OPTION = 2;
    private static final int ADD_TEAM_COLLABORATOR_OPTION = 3;

    //STATISTICS
    private static final int CRITICITY_REGISTER_OPTION = 1;
    private static final int SHOW_SLA = 2;

    //WORKFLOW
    private static final int WORKFLOW_STATE_OPTION = 1;

    //TEAM
    private static final int TEAM_REGISTER_OPTION = 1;
    private static final int TEAM_TYPE_REGISTER_OPTION = 2;

    //SERVICES
    private static final int SERVICE_REGISTER_OPTION = 1;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int COLLABORATOR_OPTION = 3;
    private static final int TEAM_OPTION = 4;
    private static final int CATALOGS_OPTION = 5;
    private static final int SERVICES_OPTION = 6;
    private static final int STATISTIC_OPTION = 7;
    private static final int SETTINGS_OPTION = 8;

    //RH MANAGER MAIN MENU
    private static final int HR_COLLABORATOR_OPTION = 2;
    private static final int HR_TEAMS_OPTION = 3;

    //SERVICES MANAGER MAIN MENU
    private static final int SERVICES_CATALOG_OPTION = 2;
    private static final int SERVICES_SERVICE_OPTION = 3;
    private static final int SERVICES_CRITICITY_OPTION = 4;
    private static final int SERVICES_WORKFLOW_OPTION = 5;

    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu collaboratorsMenu = buildCollaboratorsMenu();
            mainMenu.addSubMenu(COLLABORATOR_OPTION, collaboratorsMenu);
            final Menu teamsMenu = buildTeamsMenu();
            mainMenu.addSubMenu(TEAM_OPTION, teamsMenu);
            final Menu catalogsMenu = buildCatalogsMenu();
            mainMenu.addSubMenu(CATALOGS_OPTION, catalogsMenu);
            final Menu servicesMenu = buildServicesMenu();
            mainMenu.addSubMenu(SERVICES_OPTION, servicesMenu);
            final Menu statisticsMenu = buildStatisticsMenu();
            mainMenu.addSubMenu(STATISTIC_OPTION, statisticsMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.HR_MANAGER)) {
            final Menu collaboratorsMenu = buildCollaboratorsMenu();
            mainMenu.addSubMenu(HR_COLLABORATOR_OPTION, collaboratorsMenu);
            final Menu teamsMenu = buildTeamsMenu();
            mainMenu.addSubMenu(HR_TEAMS_OPTION, teamsMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.SERVICES_MANAGER)) {
            final Menu catalogsMenu = buildCatalogsMenu();
            mainMenu.addSubMenu(SERVICES_CATALOG_OPTION, catalogsMenu);
            final Menu servicesMenu = buildServicesMenu();
            mainMenu.addSubMenu(SERVICES_SERVICE_OPTION, servicesMenu);
            final Menu criticityMenu = buildStatisticsMenu();
            mainMenu.addSubMenu(SERVICES_CRITICITY_OPTION, criticityMenu);
            final Menu workflowMenu = buildWorkflowMenu();
            mainMenu.addSubMenu(SERVICES_WORKFLOW_OPTION, workflowMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(SET_KITCHEN_ALERT_LIMIT_OPTION, "Set kitchen alert limit",
                new ShowMessageAction("Not implemented yet"));
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCatalogsMenu() {
        final Menu menu = new Menu("Manage Catalogs >");

        menu.addItem(CATALOG_REGISTER_OPTION, "Add Catalog", new RegisterCatalogUI()::show);
        menu.addItem(CATALOG_ASSIGN_CRITICALITY_OPTION, "Assign Criticality to Catalogs", new AssignCriticalityToCatalogUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildServicesMenu() {
        final Menu menu = new Menu("Manage Services >");

        menu.addItem(SERVICE_REGISTER_OPTION, "Specify Service", new SpecifyServiceUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCollaboratorsMenu() {
        final Menu menu = new Menu("Manage Collaborators >");

        menu.addItem(COLLABORATOR_REGISTER_OPTION, "Add Collaborator", new RegisterCollaboratorUI()::show);
        menu.addItem(REMOVE_TEAM_COLLABORATOR_OPTION, "Remove Team Collaborator", new RemoveTeamCollaboratorUI()::show);
        menu.addItem(ADD_TEAM_COLLABORATOR_OPTION, "Add Team Collaborator", new AddTeamCollaboratorUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildTeamsMenu() {
        final Menu menu = new Menu("Manage Teams >");

        menu.addItem(TEAM_REGISTER_OPTION, "Add Team", new CreateTeamUI()::show);
        menu.addItem(TEAM_TYPE_REGISTER_OPTION, "Add Team Type", new RegisterTeamTypeUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildStatisticsMenu() {
        final Menu menu = new Menu("Statistics >");

        menu.addItem(CRITICITY_REGISTER_OPTION, "Add Criticity", new DefineCriticityUI()::show);
        menu.addItem(SHOW_SLA, "Show Objectives Sla", new ShowSlaUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    public Menu buildWorkflowMenu(){
        final Menu menu =  new Menu("Workflow >");

        menu.addItem(WORKFLOW_STATE_OPTION, "Verify Workflow State", new WorkflowStateUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

}
