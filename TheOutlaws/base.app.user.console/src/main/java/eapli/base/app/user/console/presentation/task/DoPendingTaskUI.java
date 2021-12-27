/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.user.console.presentation.task;

import eapli.base.languagemanagement.application.FormValidator;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.servicemanagement.domain.Attribute;
import eapli.base.servicemanagement.domain.Form;
import eapli.base.taskmanagement.application.DoPendingTaskController;
import eapli.base.taskmanagement.domain.ApprovalTask;
import eapli.base.taskmanagement.domain.ManualTask;
import eapli.base.taskmanagement.domain.Task;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author migue
 */
public class DoPendingTaskUI extends AbstractUI {

    private final DoPendingTaskController theController = new DoPendingTaskController();

    @Override
    protected boolean doShow() {
        final List<TaskExecution> tasks = this.theController.searchPendingTasks();
        if (tasks == null || tasks.isEmpty()) {
            System.out.println("Can't search tasks since there is none pending tasks of this collaborator\n");
            return false;
        }

        int i = 1;
        System.out.println("Pending Tasks of this Collaborator:");
        for (TaskExecution t : tasks) {
            System.out.printf("%d - %s\n", i, t);
            i++;
        }

        int option;
        do {
            System.out.println("1 - Execute One Task");
            System.out.println("0 - Exit");

            option = Console.readInteger("Select an option:\n");

            if (option == 0) {
                return false;
            }
            if (option == 1) {
                int option2 = Console.readInteger("\nSelect a Task to execute");
                TaskExecution task = tasks.get(option2 - 1);
                int taskType = theController.verifyTaskType(task);

                theController.showRequestDetails(task);

                if (taskType == 1) {//tarefa de aprovação
                    ApprovalTask approvalTask = (ApprovalTask) task.task();
                    Form form = approvalTask.forms();
                    List<Attribute> attributeL = form.atributes();
                    List<String> formResponse = new ArrayList<>();
                    
                    theController.showFormResponse(task, true);

                    formResponse = formResponse(attributeL, form);
                    Boolean isTaskApproval = askForApproval(true);

                    task = theController.doApprovalTask(task, formResponse);

                    try {
                        task = theController.calculateResolutionTime(task);
                        theController.doApprovalTask(task, isTaskApproval);
                    } catch (ParseException ex) {
                        Logger.getLogger(DoPendingTaskUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (taskType == 2) {//tarefa de resolução
                    ManualTask manualTaskTask = (ManualTask) task.task();
                    Form form = manualTaskTask.forms();
                    List<Attribute> attributeL = form.atributes();
                    List<String> formResponse = new ArrayList<>();
                    
                    theController.showFormResponse(task, false);

                    formResponse = formResponse(attributeL, form);

                    task = theController.doResolutionTask(task, formResponse);
                    try {
                        theController.calculateResolutionTime(task);
                    } catch (ParseException ex) {
                        Logger.getLogger(DoPendingTaskUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("Task Type Invalid. Please try again");
                }

            }

        } while (option != 0);

        return false;

    }

    public List<String> formResponse(List<Attribute> attributeL, Form form) {
        List<String> formResponse = new ArrayList<>();
        System.out.println("Complete the following attributes:");
        do {
            formResponse = new ArrayList<>();
            for (Attribute attr : attributeL) {
                String attributeResponse;
                int valido = 1;
                do {
                    System.out.printf("-%s(%s)\n", attr.name(), attr.description());
                    attributeResponse = Console.readLine("");

                    if (!(this.theController.isValidByExpressaoRegular(attributeResponse, attr.regularExpression().toString()))) {
                        valido = 0;
                        System.out.println("Atribute is not valid");
                    } else {
                        valido = 1;
                    }
                } while (valido == 0);

                formResponse.add(attributeResponse);
            }
        } while (!FormValidator.verifyData(form.script(), formResponse, attributeL));

        return formResponse;
    }

    public boolean askForApproval(boolean isApprovalTask) {
        int option;
        if (isApprovalTask) {
            do {
                System.out.println("Do you approve this task");
                System.out.println("1 - Yes");
                System.out.println("2 - No");
                option = Console.readInteger("Select an option:\n");
            } while (option < 1 && option > 2);
            if (option == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String headline() {
        return "Do Pending Tasks";
    }

}
