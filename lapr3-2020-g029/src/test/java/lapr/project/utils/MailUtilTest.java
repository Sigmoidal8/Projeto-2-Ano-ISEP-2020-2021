/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.model.Address;
import lapr.project.model.Pharmacy;

import org.junit.jupiter.api.Test;


public class MailUtilTest {

    public MailUtilTest() {
    }

    /**
     * Test of sendEmail method, of class MailUtil.
     */
    @Test
    public void testSendEmail() throws Exception {
        System.out.println("sendEmail");

        /**
         * Change to your e-mail
         */
        String recepient = "lapr3g029@gmail.com";
        String message = "Email Sent";
        String subject = "LARP3 Test";
        Pharmacy p = new Pharmacy("Pharmacy", "lapr3g029@gmail.com", "Lapr32021", new Address("Street", 5, "2000-130", "Locality", 0.0, 0.0));
        MailUtil mail = new MailUtil();
        mail.sendEmail(recepient, message, subject, p);
        //Email sent to the e-mail above
    }
    
    /**
     * Test of sendEmail method, of class MailUtil.
     */
    @Test
    public void testSendEmail2() throws Exception {
        System.out.println("sendEmail");

        /**
         * Change to your e-mail
         */
        String recepient = "";
        String message = "Email Sent";
        String subject = "LARP3 Test";
        Pharmacy p = new Pharmacy("Pharmacy", "lapr3g029@gmail.com", "Lapr32021", new Address("Street", 5, "2000-130", "Locality", 0.0, 0.0));
        MailUtil mail = new MailUtil();
        mail.sendEmail(recepient, message, subject, p);
        //Email sent to the e-mail above
    }

}
