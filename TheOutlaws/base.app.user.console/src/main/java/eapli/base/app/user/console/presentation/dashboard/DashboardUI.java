/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.user.console.presentation.dashboard;

import eapli.base.dashboardmanagement.application.DashboardController;
import eapli.framework.presentation.console.AbstractUI;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author migue
 */
public class DashboardUI extends AbstractUI {

    private final DashboardController theController = new DashboardController();

    @Override
    protected boolean doShow() {
        theController.showDashboard();
        URI uri;
        try {
            uri = new URI("https://localhost:50001");
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
