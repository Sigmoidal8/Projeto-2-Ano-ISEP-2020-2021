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
package eapli.base.app.user.console.presentation;

import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.app.user.console.presentation.catalogs.SearchCatalogUI;
import eapli.base.app.user.console.presentation.request.GiveFeedbackUI;
import eapli.base.app.user.console.presentation.request.ShowRequestUI;
import eapli.base.app.user.console.presentation.task.ClaimTaskUI;
import eapli.base.app.user.console.presentation.task.DoPendingTaskUI;
import eapli.base.app.user.console.presentation.task.SearchTaskUI;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * @author Paulo Gandra Sousa
 */
class MainMenu extends ClientUserBaseUI {

    private static final String SEPARATOR_LABEL = "--------------";

    private static final String RETURN = "Return ";

    private static final String NOT_IMPLEMENTED_YET = "Not implemented yet";

    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int CATALOG_OPTION = 2;
    private static final int TASK_OPTION = 3;
    private static final int REQUEST_OPTION = 4;

    //CATALOG
    private static final int SEARCH_REQUEST_SERVICE = 1;

    //task
    private static final int CLAIM_TASK = 1;
    private static final int SEE_PENDING_TASK = 2;
    private static final int EXECUTE_PENDING_TASK = 3;

    //Request
    private static final int SHOW_REQUEST = 1;
    private static final int GIVE_FEEDBACK = 2;

    private final AuthorizationService authz
            = AuthzRegistry.authorizationService();

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
        final MenuRenderer renderer
                = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);
        mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.COLLABORATOR, BaseRoles.SERVICES_MANAGER, BaseRoles.HR_MANAGER)) {
            final Menu catalogsMenu = buildCatalogsMenu();
            mainMenu.addSubMenu(CATALOG_OPTION, catalogsMenu);
            final Menu tasksMenu = buildTasksMenu();
            mainMenu.addSubMenu(TASK_OPTION, tasksMenu);
            final Menu requestMenu = buildRequestMenu();
            mainMenu.addSubMenu(REQUEST_OPTION, requestMenu);
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    public Menu buildCatalogsMenu() {
        final Menu menu = new Menu("Manage Catalogs >");

        menu.addItem(SEARCH_REQUEST_SERVICE, "Search Catalog and Request Service", new SearchCatalogUI()::show);
        menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);

        return menu;
    }

    public Menu buildTasksMenu() {
        final Menu menu = new Menu("Manage Tasks >");

        menu.addItem(CLAIM_TASK, "Search and Claim Tasks", new ClaimTaskUI()::show);
        menu.addItem(SEE_PENDING_TASK, "List Pending Tasks", new SearchTaskUI()::show);
        menu.addItem(EXECUTE_PENDING_TASK, "Execute a Task", new DoPendingTaskUI()::show);
        menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);

        return menu;
    }

    public Menu buildRequestMenu() {
        final Menu menu = new Menu("Manage Request >");

        menu.addItem(SHOW_REQUEST, "Show Request", new ShowRequestUI()::show);
        menu.addItem(GIVE_FEEDBACK, "Give Feedback to a Request", new GiveFeedbackUI()::show);
        menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);

        return menu;
    }
}
