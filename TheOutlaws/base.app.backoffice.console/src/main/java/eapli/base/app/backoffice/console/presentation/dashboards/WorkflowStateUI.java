package eapli.base.app.backoffice.console.presentation.dashboards;

import eapli.base.dashboardmanagement.workflow.application.WorkflowDashboardController;
import eapli.framework.presentation.console.AbstractUI;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Raúl Coelho
 */
public class WorkflowStateUI extends AbstractUI {

    private final WorkflowDashboardController theController = new WorkflowDashboardController();

    @Override
    protected boolean doShow() {
        theController.showDashboard();
        URI uri;
        try {
            uri = new URI("https://localhost:50002");
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String headline() {
        return "Dasboard";
    }
}
