package com.example.demo.mail;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.derby.tools.sysinfo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendSimpleMail {
	
	@RequestMapping(value="/send" , method= RequestMethod.GET)
	public String sendTemplateEmail() {

        Properties props = new Properties();  
        props.put("mail.smtp.host", "smtp.gmail.com");  
        props.put("mail.smtp.auth", "true");  
        props.put("mail.debug", "true");  
        props.put("mail.smtp.port", 465);  
        props.put("mail.smtp.socketFactory.port", 465);  
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.transport.protocol", "smtp");

        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        Session mailSession = null;

        mailSession = Session.getInstance(props,  
                new javax.mail.Authenticator() {  
            protected PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication("lenaasfour277@gmail.com", ";as41996*/;");  
            }  
        });  


        try {
        	System.out.println("bad");

            Transport transport = mailSession.getTransport();
        	System.out.println("bad");


            MimeMessage message = new MimeMessage(mailSession);
        	System.out.println("bad");

            message.setSubject("Sample Subject");
        	System.out.println("bad");

            message.setFrom(new InternetAddress("lenaasfour277@gmail.com"));
        	System.out.println("bad");

            message.addRecipient(Message.RecipientType.TO, new InternetAddress("asfour.deena@gmail.com"));

            String body = "Sample text";
            
            message.setContent(body,"text/html");

         transport.connect("smtp.gmail.com","lenaasfour277@gmail.com",";as41996*/;");
        	System.out.println("bad");


     transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (Exception exception) {
       	System.out.println(exception.getMessage());

        }
        return "good";
    }
} 