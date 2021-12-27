/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.user.console.presentation.task;

import eapli.base.taskmanagement.application.ClaimTaskController;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.Date;

public class ClaimTaskUI extends AbstractUI {

    private final ClaimTaskController theController = new ClaimTaskController();

    @Override
    protected boolean doShow() {
        final Iterable<TaskExecution> tasks = this.theController.findAvailableTasks();
        if (tasks == null) {
            System.out.println("Can't search tasks since there is none task for this collaborator\n");
            return false;
        }

        int i = 1;
        System.out.println("Tasks for this Collaborator:");
        for (TaskExecution t : tasks) {
            System.out.printf("%s\n", t);
            i++;
        }

        int option;
        do {

            System.out.println("1 - Order");
            System.out.println("2 - Filter");
            System.out.println("3 - Claim");
            System.out.println("0 - Exit");

            option = Console.readInteger("Select an option:\n");

            if (option == 0) {
                return false;
            }
            if (option == 1) {

                System.out.println("1 - Priority");
                System.out.println("2 - Criticality");
                System.out.println("3 - Completion deadline");

                int option2 = Console.readInteger("\nSelect an Order Option");

                switch (option2) {
                    case 1:
                        Iterable<TaskExecution> tasksWithPriority = theController.orderByPriority(tasks);

                        int j = 1;
                        System.out.println("Tasks for this Collaborator Ordered by Priority:");
                        for (TaskExecution t : tasksWithPriority) {
                            System.out.printf("%d- %s\n", j, t);
                            j++;
                        }
                        break;

                    case 2:
                        Iterable<TaskExecution> tasksWithCriticality = theController.orderByCriticality(tasks);

                        int k = 1;
                        System.out.println("Tasks for this Collaborator Ordered by Criticality:");
                        for (TaskExecution t : tasksWithCriticality) {
                            System.out.printf("%d- %s\n", k, t);
                            k++;
                        }
                        break;
                    case 3:
                        Iterable<TaskExecution> tasksWithDeadline = theController.orderByCompletationDeadline(tasks);

                        int l = 1;
                        System.out.println("Tasks of this Collaborator Ordered by Deadline:");
                        for (TaskExecution t : tasksWithDeadline) {
                            System.out.printf("%d- %s\n", l, t);
                            l++;
                        }
                        break;
                    default:
                        System.out.println("\nOption does not exist");
                        return false;
                }
            }

            if (option == 2) {

                System.out.println("1 - Priority");
                System.out.println("2 - Criticality");
                System.out.println("3 - Completion deadline");

                int option2 = Console.readInteger("\nSelect an Filter Option");

                switch (option2) {
                    case 1:
                        String optionPriority = Console.readLine("\nInsert Priority");
                        Iterable<TaskExecution> tasksWithPriority = theController.filterByPriority(tasks, optionPriority);

                        int j = 1;
                        System.out.println("Tasks for this Collaborator Filtered by Priority:");
                        for (TaskExecution t : tasksWithPriority) {
                            System.out.printf("%d- %s\n", j, t);
                            j++;
                        }
                        break;

                    case 2:
                        int optionCriticicality = Console.readInteger("\nInsert Criticality");
                        Iterable<TaskExecution> tasksWithCriticality = theController.filterByCriticality(tasks, optionCriticicality);

                        int k = 1;
                        System.out.println("Tasks for this Collaborator Filtered by Criticality:");
                        for (TaskExecution t : tasksWithCriticality) {
                            System.out.printf("%d- %s\n", k, t);
                            k++;
                        }
                        break;
                    case 3:
                        Date optionDeadline = Console.readDate("\nInsert Deadline","dd/MM/yyyy");
                        Iterable<TaskExecution> tasksWithDeadline = theController.filterByCompletationDeadline(tasks, optionDeadline);

                        int l = 1;
                        System.out.println("Tasks for this Collaborator Filtered by Deadline:");
                        for (TaskExecution t : tasksWithDeadline) {
                            System.out.printf("%d- %s\n", l, t);
                            l++;
                        }
                        break;
                    default:
                        System.out.println("\nOption does not exist");
                        return false;
                }
            }
            if (option == 3) {

                final SelectWidget<TaskExecution> selector1 = new SelectWidget<>("Tasks:", tasks, new TaskExecutionPrinter());
                selector1.show();
                final TaskExecution theTask = selector1.selectedElement();

                if (theTask != null) {
                    this.theController.claimTask(theTask);
                    System.out.println("Task Claimed!");
                }
            }

        } while (option != 0);

        return false;
    }

    @Override

    public String headline() {
        return "Claim Tasks";
    }

}
