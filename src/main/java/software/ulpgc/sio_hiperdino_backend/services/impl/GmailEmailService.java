package software.ulpgc.sio_hiperdino_backend.services.impl;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.ulpgc.sio_hiperdino_backend.services.EmailService;

import java.util.List;
import java.util.Properties;

@Service
public class GmailEmailService implements EmailService {
    private final Logger log = LoggerFactory.getLogger(GmailEmailService.class);
    private final String fromMail;
    private final String password;
    private final Properties properties;
    private final Session session;

    public GmailEmailService(Dotenv dotenv) {
        fromMail = dotenv.get("EMAIL_USERNAME");
        password = dotenv.get("EMAIL_PASSWORD");
        if(fromMail == null) {
            log.error("EMAIL_USERNAME not found in .env file");
        }
        if(password == null) {
            log.error("EMAIL_PASSWORD not found in .env file");
        }
        properties = createProperties();
        session = createSession();
    }

    private Properties createProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return properties;
    }

    private Session createSession() {
        return Session.getInstance(this.properties,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromMail, password);
                    }
                }
        );
    }

    @Override
    public void sendEmail(List<String> recipients, String subject, String body) {
        try {
            if(fromMail == null || password == null) {
                return;
            }
            Message message = createMessage(recipients, subject, body);
            Transport.send(message);
            System.out.printf("Email sent to %s%n", String.join(", ", recipients));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Message createMessage(List<String> recipients, String subject, String body) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromMail));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(String.join(", ", recipients))
        );
        message.setSubject(subject);
        message.setText(body);
        return message;
    }
}
