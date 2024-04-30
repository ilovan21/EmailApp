package org.example;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailApp {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // recipient
        System.out.println("To:");
        String sendTo = reader.readLine();

        // de la
        String from = "maria17.ilovan@gmail.com";

        // parola
        final String username = "maria17.ilovan@gmail.com";
        final String password = "jmta guxx acqc dhrv";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));

            // Subiect
            System.out.println("Subject:");
            String subject = reader.readLine();
            message.setSubject(subject);

            System.out.println("Message:");
            String mess = reader.readLine();
            message.setText(mess);

            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(mess, "text/html");

            // trimitere mesaj
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException | IOException mex) {
            mex.printStackTrace();
            System.out.println("Failed to send message: " + mex.getMessage());
        }
    }
}