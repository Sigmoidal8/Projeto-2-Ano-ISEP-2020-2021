package eapli.base.servicemanagement.domain;

import org.junit.Test;

/**
 * @author Raúl Coelho
 */
public class BriefDescriptionTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureBriefDescriptionNotOversized() {
        System.out.println("Ensure BriefDescription Cant Be Oversized");

        BriefDescription bd = new BriefDescription("11111111111111111111111111111111111111111111111111111111111111");
    }
}
