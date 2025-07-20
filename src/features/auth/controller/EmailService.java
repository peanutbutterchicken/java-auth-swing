package auth.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import auth.model.Users;

import java.io.FileReader;
import java.io.IOException;

public class EmailService {
    public void sendEmail(String textMessage) {
        // MODIFY:
        // THIS METHOD USES (.properties) FILE FOR CREDENTIALS USED IN JAVAMAIL API
        //
        // THIS CREDENTIALS ARE;
        // 1. A GMAIL ACCOUNT
        // 2. AN APP PASSWORD, THIS CAN BE OBTAINED THROUGH YOUR GOOGLE ACCOUNT
        //
        // YOU CAN FOLLOW THIS FORMAT FOR YOUR (.properties) FILE:
        //
        // senderEmail: YOUR@gmail.com
        // appPassword: YOURAPPPASSWORD
        //
        // THEN CREATE A (config) FOLDER THAT WILL HAVE THE (.properties) FILE
        // NAME YOUR FILE AS "SenderEmailCredentials.properties" SO YOU DON'T HAVE TO CHANGE OTHER PARTS OF THE CODE

        // Recipient's email
        String to = Users.getCurrentUser().getEmail();

        // Sender's email using properties file. File is not inside the repo to hide credentials. Problem is when the source code is run on another machine, have to make proper alternative.
        Properties credentialProps = new Properties();
        String senderEmail = null, senderAppPassword = null;

        try(FileReader fr = new FileReader("config/SenderEmailCredentials.properties")){ 
            credentialProps.load(fr);
            senderEmail = credentialProps.getProperty("senderEmail");
            senderAppPassword = credentialProps.getProperty("appPassword");

        } catch(IOException e){
            System.err.println("Error reading properties file: " + e.getMessage());
            e.printStackTrace();
        }

        if (senderEmail == null || senderAppPassword == null) {
            System.err.println("Sender email or password not set. Exiting.");
            return;
        }

        // Initiate Email Service to send a simple text message:
        String from = senderEmail;
        final String username = senderEmail;
        final String password = senderAppPassword;

        String host = "smtp.gmail.com"; // Host to change accordingly.

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587"); // Value to change accordingly.

        // Get the Session object.
        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            // Set Subject: header field
            message.setSubject("Amaia LMS: Verification Code");
            
            // Now set the actual message
            message.setText(textMessage);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
    }

    public static boolean validateEmail(String email){
        boolean result = true;
        try{
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
        }
        catch(AddressException e){
            result = false;
        }
        return result;
    }
}
