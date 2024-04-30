package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class Rec {

    public static void receiveEmailPOP3(String pop3Host, String storeType,
                                        String user, String password) {
        try {
            // Setăm proprietățile pentru sesiunea POP3
            Properties properties = new Properties();
            properties.put("mail.pop3.host", pop3Host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.ssl.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            // Creăm obiectul Store și ne conectăm la serverul POP3
            Store emailStore = emailSession.getStore(storeType);
            emailStore.connect(pop3Host, user, password);

            // Deschidem folderul INBOX
            Folder emailFolder = emailStore.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // Obținem și afișăm mesajele
            Message[] messages = emailFolder.getMessages();
            for (int i = 0; i < 50; i++) {
                Message message = messages[i];
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
            }

            // inchidem folderul si store-ul
            emailFolder.close(false);
            emailStore.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void sendEmailWithAttachment(String username, String password) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // recipient
        System.out.println("To:");
        String sendTo = reader.readLine();

        // de la
        String from = "maria17.ilovan@gmail.com";


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

            // atasament
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(new File("C:/Users/Asus/OneDrive - Technical University of Moldova/Desktop/yolo_model.png"));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);
            multipart.addBodyPart(attachmentBodyPart);
            message.setContent(multipart);

            // trimitere mesaj
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException | IOException mex) {
            mex.printStackTrace();
            System.out.println("Failed to send message: " + mex.getMessage());
        }
    }

    public static void receiveEmailIMAP(String imapHost, String storeType,
                                        String user, String password) {
        try {
            // Setăm proprietățile pentru sesiunea IMAP
            Properties properties = new Properties();
            properties.put("mail.store.protocol", storeType);
            properties.put("mail.imap.host", imapHost);
            properties.put("mail.imap.port", "993");
            properties.put("mail.imap.ssl.enable", "true");

            // obține sesiunea IMAP
            Session emailSession = Session.getDefaultInstance(properties);

            // creare obiect Store și comectare la serverul IMAP
            Store store = emailSession.getStore(storeType);
            store.connect(imapHost, user, password);

            // deschidere folder inbox
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // obtinere și afișare mesaje
            Message[] messages = emailFolder.getMessages();
            for (int i = 0; i < 50; i++) {
                Message message = messages[i];
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
            }

            // inchidere folder si store
            emailFolder.close(false);
            store.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void sendEmail(String username, String password) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // recipient
        System.out.println("To:");
        String sendTo = reader.readLine();

        // de la
        String from = "maria17.ilovan@gmail.com";

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

    public static void main(String[] args) throws IOException {


        String username = "maria17.ilovan@gmail.com";
        String password = "jmta guxx acqc dhrv";
        while (true) {
            System.out.println("Selectati o optiune");
            System.out.println("1. Afisare lista de email protocolul POP3");
            System.out.println("2. Afisare lista de email protocolul IMAP");
            System.out.println("3. Trimite email cu atasament");
            System.out.println("4.Trimite email doar cu text");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int option = Integer.parseInt(reader.readLine());

            switch (option) {
                case 1:
                    String pop3Host = "pop.gmail.com";
                    String pop3StoreType = "pop3";

                    receiveEmailPOP3(pop3Host, pop3StoreType, username, password);
                    break;
                case 2:
                    String imapHost = "imap.gmail.com";
                    String imapStoreType = "imap";

                    receiveEmailIMAP(imapHost, imapStoreType, username, password);
                    break;
                case 3:
                    sendEmailWithAttachment(username, password);
                    break;
                case 4:
                    sendEmail(username, password);
                    break;
                default:
                    System.out.println("Opțiune invalidă.");
            }
        }
    }
}
