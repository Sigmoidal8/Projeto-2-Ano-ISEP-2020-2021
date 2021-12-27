package eapli.base.requestmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

/**
 * @author Raúl Coelho
 */
@Embeddable
public class Feedback implements ValueObject {

    private Integer value;

    public Feedback(int value){
        Preconditions.ensure(value >= 0 && value <=5);
        this.value=value;
    }

    protected Feedback(){
        //ORM only
    }

    public String toString(){
        return String.format("Feedback: %d", value);
    }
}
