package org.example.utils;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSender {

    public void sendEmail(String targetEmail, String title, String content){

        String username = "1455196414@qq.com";
        String password = "lzavklymwobofide";

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        System.out.println(session);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("1455196414@qq.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(targetEmail)
            );
            message.setSubject(title);
            message.setText(content);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
