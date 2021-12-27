package eapli.base.taskmanagement.domain;

import eapli.base.servicemanagement.domain.*;
import eapli.framework.general.domain.model.Description;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raúl Coelho
 */
public class AutomaticTaskTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureTaskExecutionCantHaveNullScript() {
        System.out.println("Ensure TaskExecution Cant Have Null Script");

        AutomaticTask t = new AutomaticTask(TaskType.Approval, 0, null);
    }

}
