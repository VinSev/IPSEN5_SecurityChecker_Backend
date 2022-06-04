package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanCategory;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.EmailService;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/mail")
@CrossOrigin
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private PdfService pdfService;




    @PostMapping()
    public void mail(@RequestBody Scan scan) throws MessagingException, IOException {
        this.pdfService.makePdf(scan);
//        this.emailService.sendEmailWithPDFAttachment(scan);
    }

}
