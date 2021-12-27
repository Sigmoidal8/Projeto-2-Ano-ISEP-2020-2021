package eapli.base.servicemanagement.domain;

import org.junit.Test;

/**
 * @author Raúl Coelho
 */
public class TitleTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureTitleNotOversized() {
        System.out.println("Ensure Title Cant Be Oversized");

        Title title = new Title("111111111111111111111111111111111");
    }
}
