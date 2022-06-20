package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String adminMail;

    public void sendEmailWithPDFAttachment(Scan scan) throws MessagingException {
        Date date = new Date();

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(adminMail);
        messageHelper.setTo(scan.getUser().getEmail());

        messageHelper.setSubject("Get Big Marketing Security Scan Resultaten");
        messageHelper.setText(String.format(
                "Beste %s, \n\n" +
                "Op %s heeft u bij on een scan gedaan." +
                "De uitgebreide uitslag voor deze scan staat in de bijlage als pdf. \n\n" +
                "Bedankt voor het scannen bij Bet Big Marketing. \n\n" +
                "Met vriendelijke groet, \n\n" +
                "Het Get Big Marketing Team", scan.getUser().getName(), date));
        messageHelper.addAttachment(String.format("Get Big Marketing Security Scan Resultaten - %s", new Date()), new ClassPathResource("test.pdf"));

        javaMailSender.send(message);
    }

}
