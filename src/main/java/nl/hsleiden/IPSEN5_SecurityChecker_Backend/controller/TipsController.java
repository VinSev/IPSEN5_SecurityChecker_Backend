package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tips")
@CrossOrigin
public class TipsController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void get() {

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public void post() {

    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void put() {

    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete() {

    }
}
