/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.collaboratormanagement.aplication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.activation.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author migue
 */
public class SendEmailService {
    
    /**
     * The Session.
     */
    private final Session sessao;

    /**
     * The User Name.
     */
    private final String username;

    /**
     * Instantiates a new Email handler.
     *
     * @throws IOException the io exception
     */
    public SendEmailService() throws IOException {
        Properties appProperties = new Properties();
        String propFileName = "application.properties";

        InputStream inptStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inptStream != null) {
            appProperties.load(inptStream);
        } else {
            throw new FileNotFoundException("Application property does not exist");
        }

        inptStream.close();

        username = appProperties.getProperty("email.from", "helpdesktheoutlaws@gmail.com");
        final String password = appProperties.getProperty("email.password", "helpdeskoutlaws");
        String host = appProperties.getProperty("email.host", "smtp.gmail.com");
        String port = appProperties.getProperty("email.port", "587");

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        sessao = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

    }


    /**
     * Send email boolean.
     *
     * @param dest    the dest
     * @param subject the subject
     * @param content the content
     * @return the boolean
     */
    public boolean sendEmail(String dest, String subject, String content) {
        try {
            javax.mail.Message msg = new MimeMessage(sessao);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dest));
            msg.setSubject(subject);
            msg.setText(content);
            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }
}
    