package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailWithPDFAttachment(String email, String name) throws MessagingException {
        Date date = new Date();

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setTo(email);

        messageHelper.setSubject("Get Big Marketing Security Scan Resultaten");
        messageHelper.setText(String.format(
                "Beste %s, \n\n" +
                "Op %s heeft u bij on een scan gedaan." +
                "De uitgebreide uitslag voor deze scan staat in de bijlage als pdf. \n\n" +
                "Bedankt voor het scannen bij Bet Big Marketing. \n\n" +
                "Met vriendelijke groet, \n\n" +
                "Het Get Big Marketing Team", name, date));
        messageHelper.addAttachment(String.format("Get Big Marketing Security Scan Resultaten.%s", new Date()), new ClassPathResource("resultaten.pdf"));

        javaMailSender.send(message);
    }

}
