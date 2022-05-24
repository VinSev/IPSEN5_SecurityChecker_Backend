package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanCategory;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/mail")
@CrossOrigin
public class EmailController {
    @Autowired
    private EmailService emailService;




    @PostMapping()
    public void mail(@RequestBody Scan scan) throws MessagingException {

        ArrayList<ScanCategory> scans = scan.getScanCategories();
        scans.forEach(tester ->{
            System.out.println(tester.getGrade());
        });


//        this.emailService.sendEmailWithPDFAttachment(scan);
    }

}
