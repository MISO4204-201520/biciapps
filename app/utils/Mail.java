package utils;


import javax.mail.*;
import javax.mail.internet.*;

import models.entities.User;

/**
 * Created by Omar on 11/10/2015.
 */
public class Mail {

        public static void sendMailAdmin(String[] to, String subject, String body) {
            PropertyReader.initializePropertyReader();
            String from = PropertyReader.getPropertyValue("userAdmin");
            String pass = PropertyReader.getPropertyValue("pass");
            String host = PropertyReader.getPropertyValue("mail.smtp.host");

            Session session = Session.getDefaultInstance(PropertyReader.getProperties());

            MimeMessage message = new MimeMessage(session);

            try {
                message.setFrom(new InternetAddress(from));
                InternetAddress[] toAddress = new InternetAddress[to.length];

                // To get the array of addresses
                for (int i = 0; i < to.length; i++) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
                }

                message.setSubject(subject);
                message.setText(body);

                Transport transport = session.getTransport("smtp");
                transport.connect(host, from, pass);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            } catch (AddressException ae) {
                ae.printStackTrace();
            } catch (MessagingException me) {
                me.printStackTrace();
            }
        }
        
        public static void sendMailUser(String[] to, User user, String subject, String body) {
            PropertyReader.initializePropertyReader();

            String from = user.getEmail();
            String pass = user.getPwd();
            
            String host = PropertyReader.getPropertyValue("mail.smtp.host");

            Session session = Session.getDefaultInstance(PropertyReader.getProperties());

            MimeMessage message = new MimeMessage(session);

            try {
                message.setFrom(new InternetAddress(from));
                InternetAddress[] toAddress = new InternetAddress[to.length];

                // To get the array of addresses
                for (int i = 0; i < to.length; i++) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
                }

                message.setSubject(subject);
                message.setText(body);

                Transport transport = session.getTransport("smtp");
                transport.connect(host, from, pass);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            } catch (AddressException ae) {
                ae.printStackTrace();
            } catch (MessagingException me) {
                me.printStackTrace();
            }
        }
}

