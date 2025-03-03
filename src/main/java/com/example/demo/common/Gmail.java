package com.example.demo.common;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.stereotype.Service;

@Service
public class Gmail {
	
	 public static void send(String to,String subject,String massage) {
	        // Sender's email ID
	        String from = "krushnadaksh55@gmail.com";

	        // SMTP server details (for Gmail)
	        String host = "smtp.gmail.com";

	        // Setup mail server properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", "587");

	        // Authenticate with the email server (Gmail in this case)
	        final String username = "krushnadaksh55@gmail.com";  // your Gmail username
	        final String password = "njcd ynno txtk hxhj";         // your Gmail password

	        // Get the default Session object with authentication
	        Session session = Session.getInstance(properties,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });

	        try {
	            // Create a default MimeMessage object.
	            Message message = new MimeMessage(session);

	            // Set From: header field
	            message.setFrom(new InternetAddress(from));

	            // Set To: header field
	            message.setRecipients(Message.RecipientType.TO,
	                    InternetAddress.parse(to));

	            // Set Subject: header field
	            message.setSubject(subject);

	            // Set the actual message
	            message.setText(massage);

	            // Send message
	            Transport.send(message);

	            System.out.println("Email sent successfully!");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	
}
