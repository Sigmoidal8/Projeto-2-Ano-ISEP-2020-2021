/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lapr.project.model.Pharmacy;


public class MailUtil {
    
    public MailUtil(){
    }
    

    public static void sendEmail(String recepient, String message, String subject, Pharmacy pharmacy) throws MessagingException {

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String emailAccount = pharmacy.getEmail();
        String password = pharmacy.getPassword();

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAccount, password);
            }
        });

        Message email = prepareMessage(session, emailAccount, recepient, message, subject);
        if (email != null)
            Transport.send(email);
    }

    private static Message prepareMessage(Session session, String emailAccount, String recepient, String message, String header) {
        try {
            Message messageToSend = new MimeMessage(session);
            messageToSend.setFrom(new InternetAddress(emailAccount));
            messageToSend.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            messageToSend.setSubject(header);
            messageToSend.setText(message);
            return messageToSend;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
