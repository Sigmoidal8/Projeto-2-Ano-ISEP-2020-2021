/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.user.console.presentation.request;

import eapli.base.requestmanagement.application.ShowRequestsController;
import eapli.base.requestmanagement.domain.Request;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import java.util.List;

/**
 *
 * @author migue
 */
public class ShowRequestUI extends AbstractUI {

    private final ShowRequestsController theController = new ShowRequestsController();
    private static final int ONGOING_REQUESTS = 1;
    private static final int DONE_REQUESTS = 2;

    @Override
    protected boolean doShow() {
        int option;
        do {

            System.out.println("1 - Show ongoing requests");
            System.out.println("2 - Show the requests already made");
            System.out.println("0 - Exit");

            option = Console.readInteger("Select an option:");
            if (option == 0) {
                return false;
            }
            if (option == 1) {
                List<Request> requests = this.theController.showRequests(ONGOING_REQUESTS);
                if (requests == null || requests.isEmpty()) {
                    System.out.println("Can't search requests since there is none ongoing request of this collaborator\n");
                } else {
                    int i = 1;
                    System.out.println("Ongoing requests of this Collaborator:");
                    System.out.println("======================================");
                    for (Request t : requests) {
                        System.out.printf("%d - %s\n", i, t);
                        i++;
                    }
                    System.out.println("======================================");
                    return showMoreDetails(requests, i);
                }
            } else if (option == 2) {
                List<Request> requests = this.theController.showRequests(DONE_REQUESTS);
                if (requests == null) {
                    System.out.println("Can't search requests since there is none done request of this collaborator\n");
                } else {
                    int i = 1;
                    System.out.println("Done requests of this Collaborator:");
                    System.out.println("======================================");
                    for (Request t : requests) {
                        System.out.printf("%d - %s\n", i, t);
                        i++;
                    }
                    System.out.println("======================================");
                    return showMoreDetails(requests, i);
                }
            }
        } while (option != 0);

        return false;
    }

    private boolean showMoreDetails(List<Request> requests, int i) {
        int option;
        do {

            System.out.println("\n1 - Show more details about one request");
            System.out.println("0 - Exit");

            option = Console.readInteger("Select an option:");

            if (option == 0) {
                return false;
            }
            if (option == 1) {

                System.out.println("About whitch request do you need more details?");
                int option2 = Console.readInteger("(choose one from the list above)");

                if (option2 <= i) {

                    Request request = requests.get(option2 - 1);
                    System.out.println("Request Details:");
                    System.out.println("================");
                    System.out.printf("Unique Code: %s;\nService: %s;\nDate Of Request: %s;\nDeadLine Date: %s;\nType: %s;\nPriority: %s;\nStatus: %s;\nTasks Executions: %s;\n",
                            request.uniqueCode(), request.service(), request.dateOfRequestt(), request.deadLineDate(), request.type(), request.priority(),
                            request.status(), request.taskExecutions());
                    System.out.println("================");
                } else {
                    System.out.println("Invalid Option");
                }

            }
        } while (option != 0);

        return false;
    }

    @Override

    public String headline() {
        return "Show Requests";
    }

}
