package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping(value = "/mail")
@CrossOrigin
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping()
    public void mail(@RequestBody Scan scan) throws MessagingException {
        this.emailService.sendEmailWithPDFAttachment(scan);
    }
}
