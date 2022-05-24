package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.HTTPResponse;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/test")
@CrossOrigin
public class testController {

//    The following code below is there to show you how to return an value to the front-end.
//    First you just do the normal this as per usual, but instead of returning the value you want to return
//    You return a HTTPResponse, this can diver from returnSucces to returnFailure, in case of returnSucces
//    you want to give the value you want to return with it.
//    In the HTTPResponse.java it wil create a new HTTPResponse and give it the given value, then it wil be
//    return to the front-end.
//    In case you want to give a returnFailure, instead of giving the value you want to return you give
//    a error message in return. (Something like: "The user input value is not correct, please send a
//    valid value")

//    Of course you do NOT handle anything inside of the controller, but in a Dao or service

    @GetMapping({"/test"})
    public HTTPResponse getTest(@RequestParam(name = "name", defaultValue = "jarry") String name) {
        System.out.println(name);
        if(true) {
            //HTTPResponse.returnSucces
            timeout();
            return HTTPResponse.returnSuccess("get: " + name);
        }
        //HTTPResponse.returnFailure
        return HTTPResponse.returnFailure("could not find results for a test");
    }
    @PostMapping({"/test"})
    public HTTPResponse postTest(@RequestParam(name = "name", defaultValue = "jarry") String name){
        timeout();
        return HTTPResponse.returnSuccess("Post: " + name);
//        return HTTPResponse.returnFailure("could not find results for a test");
    }
    @PutMapping({"/test"})
    public HTTPResponse putTest(@RequestParam(name = "name", defaultValue = "jarry") String name){
        timeout();
        return HTTPResponse.returnSuccess("Put: " + name);
    }
    @DeleteMapping({"/test"})
    public HTTPResponse deleteTest(@RequestParam(name = "name", defaultValue = "jarry") String name){
        timeout();
        return HTTPResponse.returnSuccess("Delete: " + (name));
    }

    public void timeout(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

