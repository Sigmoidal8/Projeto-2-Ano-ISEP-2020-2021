package eapli.base.requestmanagement.domain;

import org.junit.Test;

/**
 * @author Raúl Coelho
 */
public class FeedbackTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureFeedbackCantBeLowerThan0(){
        Feedback feedback = new Feedback(-3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureFeedbackCantBeHigherThan5(){
        Feedback feedback = new Feedback(7);
    }
}
