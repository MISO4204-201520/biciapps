package utils;


import javax.mail.*;
import javax.mail.internet.*;

/**
 * Created by Omar on 11/10/2015.
 */
public class Mail {

        private static String RECIPIENT = "omarcz27@gmail.com";

        public static void main(String[] args) {
            String[] to = { RECIPIENT }; // list of recipient email addresses
            String subject = "Java send mail example";
            String body = "Welcome to JavaMail!";

            sendMail(to, subject, body);
        }

        public static void sendMail(String[] to, String subject, String body) {
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
}

