/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.user.console.presentation.task;

import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Utilizador
 */
public class TaskExecutionPrinter implements Visitor<TaskExecution>{

    @Override
    public void visit(TaskExecution visitee) {
        System.out.printf("%s",visitee.toString());
    }
    
}
