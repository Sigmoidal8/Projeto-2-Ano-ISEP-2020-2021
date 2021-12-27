package eapli.base.servicemanagement.domain;

import org.junit.Test;

/**
 * @author Raúl Coelho
 */
public class CompleteDescriptionTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureCompleteDescriptionNotOversized() {
        System.out.println("Ensure CompleteDescription Cant Be Oversized");

        CompleteDescription bd = new CompleteDescription("11111111111111111111111111111111111111111111111111111111111111" +
                "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
    }
}
