package uni.fmi.RealEstate.services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import uni.fmi.RealEstate.models.Email;
import uni.fmi.RealEstate.repo.EmailRepo;

import java.util.List;

@Service
public class EmailService extends BaseService<Email> {

    private final String from = "stu2301717020@uni-plovdiv.bg";
    private final String password = "sxow gjdr lzbr ipuy";
    private final String host = "smtp.gmail.com";
    private final Properties properties = System.getProperties();
    private String to = from;
    private Session session;
    private String subject;
    private String text;

    @Autowired
    private EmailRepo emailRepo;

    @Override
    protected JpaRepository<Email, Long> getRepo() { return emailRepo; }

    public EmailService() {
        session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, password);

            }
        });
    }
    private void setUpServer() {
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        session.setDebug(true);
    }

    public List<Email> getFilteredEmailsByCompany(String filter){
        return emailRepo.findByCompanyNameContaining(filter);
    }

    public void setTemplateEmail(String fullName, String companyName,
                                 String clientEmail, String subject, String body,
                                 String emailTo){
        this.setUpServer();
        try{

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);

            System.out.println("sending...");

            Transport.send(message);

            System.out.println("Sent message successfully....");
        } catch (MessagingException e){
            e.printStackTrace();
        }
    }

    public void sendReplyMail(String title, String body, String clientEmail){
        this.setTemplateEmail("", "", "", subject, body, "");
    }
}
