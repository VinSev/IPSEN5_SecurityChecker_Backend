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
    public void getPdfInfo(@RequestBody ArrayList<ScanCategory> scans,
                           @RequestBody String name,
                           @RequestBody String website){
        System.out.println(name + website);
//        scans.forEach(scan ->{
//            System.out.println(scan);
//        });
    }
}
