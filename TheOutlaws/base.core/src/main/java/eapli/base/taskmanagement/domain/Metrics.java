package eapli.base.taskmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Raúl Coelho
 */
@Embeddable
public class Metrics implements ValueObject {

    private static final long serialVersionUID = 1L;

    private Date resolutionTime;
    
    public Metrics(Date resolutionTime) {
        this.resolutionTime = resolutionTime;
    }

    protected Metrics(){
        //ORM only
    }

    public Date resolutionTime() {
        return this.resolutionTime;
    }

    public String showResolutionTime(){
        long time = resolutionTime.getTime();
        String hms = String.format("Resolution Time: %02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(time),
                TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)),
                TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
        return hms;
    }

    public String toString(){
        return String.format("Resolution Date: %s", resolutionTime.toString());
    }
}
