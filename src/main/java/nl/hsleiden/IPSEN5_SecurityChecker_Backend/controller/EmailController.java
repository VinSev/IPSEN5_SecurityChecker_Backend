package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.Report;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.EmailService;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.PdfService;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.utility.ActiveScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/mail")
@CrossOrigin
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private PdfService pdfService;

    @PostMapping()
    public void mail(@RequestBody Report report) throws MessagingException, IOException, URISyntaxException, InterruptedException {
        this.pdfService.makePdf(report);
        TimeUnit.SECONDS.sleep(10);
        this.emailService.sendEmailWithPDFAttachment(report);
    }

}
