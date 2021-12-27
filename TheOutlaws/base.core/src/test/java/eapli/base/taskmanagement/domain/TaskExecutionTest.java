/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.taskmanagement.domain;

import eapli.base.collaboratormanagement.domain.Collaborator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Forno
 */
public class TaskExecutionTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureTaskExecutionCantHaveNullTask() {
        System.out.println("Ensure TaskExecution Cant Have Null Task");
        Task t = new Task(TaskType.Approval, 1);
        TaskExecution instance = new TaskExecution(null, TaskStatus.active);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureTaskExecutionCantHaveNullStatus() {
        System.out.println("Ensure TaskExecution Cant Have Null Status");
        Task t = new Task(TaskType.Approval, 1);
        TaskExecution instance = new TaskExecution(t, null);
    }
    

}
