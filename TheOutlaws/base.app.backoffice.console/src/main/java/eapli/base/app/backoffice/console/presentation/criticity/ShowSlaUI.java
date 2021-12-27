/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.criticity;

import eapli.base.criticitymanagement.aplication.ShowSlaController;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author migue
 */
public class ShowSlaUI extends AbstractUI {

    private final ShowSlaController theController = new ShowSlaController();

    @Override
    protected boolean doShow() {
        int option;
        do {
            System.out.println("Enter the dates you want to search between?");
            Date endDate;
            Date beginDate;
            beginDate = Console.readDate("Begin Date (DD/MM/YYYY):", "dd/MM/yyyy");
            endDate = Console.readDate("End Date (DD/MM/YYYY):", "dd/MM/yyyy");

            List<Request> requests = new ArrayList<>();
            try {
                requests = theController.searchRequestsBetweenDates(beginDate, endDate);
            } catch (ParseException ex) {
                Logger.getLogger(ShowSlaUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Request> requestsWithApprovedApprovalSla = theController.requestsWithApprovedApprovalSlas();
            List<Request> requestsWithoutApprovedResolutionSla = theController.requestsWithoutApprovedResolutionSlas();
            System.out.println("Objectives Sla:");
            System.out.println("================");
            if (requests.isEmpty()) {
                System.out.println("There are no Requests beginning and finishing between these dates");
            } else {
                for (Request r : requests) {
                    System.out.printf("Request: %s\n", r);
                    if (r.approvalTask() != null) {
                        System.out.printf(" - Request Approval Time: %s - Objective Approval Time: %s\n", convertToTime(r.approvalTask().metrics().resolutionTime()), r.service().criticality().objectives().tempoMaxAprovacao());
                        if (requestsWithApprovedApprovalSla.contains(r)) {
                            System.out.println("Task Approved\n");
                        } else {
                            System.out.println("Task Disapproved\n");
                        }
                    }
                    if (r.resolutionTask() != null) {
                        System.out.printf(" - Request Resolution Time: %s - Objective Resolution Time: %s\n", convertToTime(r.resolutionTask().metrics().resolutionTime()), r.service().criticality().objectives().tempoMaxResolucao());
                        if (requestsWithoutApprovedResolutionSla.contains(r)) {
                            System.out.println("Task Approved\n");
                        } else {
                            System.out.println("Task Disapproved\n");
                        }
                    }
                }
            }
            System.out.println("================");

            do {
                System.out.println("\n1 - Search another period of time?");
                System.out.println("0 - Exit");
                option = Console.readInteger("Select an option:");
            } while (option < 0 || option > 1);

        } while (option != 0);

        return false;
    }

    public String convertToTime(Date date) {
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(date.getTime()),
                TimeUnit.MILLISECONDS.toMinutes(date.getTime()) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(date.getTime())),
                TimeUnit.MILLISECONDS.toSeconds(date.getTime()) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(date.getTime())));
        return hms;
    }

    @Override
    public String headline() {
        return "Show Sla";
    }

}
