package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;


import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanCategory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/pdf")
public class PdfController {





    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public void getPdfInfo(@RequestBody ScanCategory scans,
                           String name,
                           String website){
        System.out.println(name + website);
        System.out.println(scans);
        System.out.println(scans.getTitle());
        System.out.println(scans.getGrade());
    }
}
