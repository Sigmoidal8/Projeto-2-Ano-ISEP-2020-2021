package protocol;

import eapli.base.languagemanagement.application.TaskValidator;
import eapli.base.requestmanagement.domain.Request;

/**
 * @author Raúl Coelho
 */
public class TaskExecutorController {

    public TaskExecutorController(){
        //empty
    }

    public int activeThreads(){
        return Thread.activeCount();
    }

    public String executeTask(Request request){
        try {
            Boolean executed = TaskValidator.executeScript(request);
            if(executed == true) {
                return "Executed";
            }else{
                return "Error executing";
            }
        }catch (Exception e){
            return "error executing";
        }
    }
}
