package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pdf")
public class PdfController {





    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public void getPdfInfo(@RequestBody String name,String website){
        System.out.println(name + website);
    }
}
