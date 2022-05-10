package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.HTTPResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
@CrossOrigin
public class testController {
    @GetMapping({"/test"})
    public HTTPResponse getTest(@RequestParam(name = "name", defaultValue = "jarry") String name){
        System.out.println(name);
        return HTTPResponse.returnSuccess("get: " + name);
    }
    @PostMapping({"/test"})
    public HTTPResponse postTest(@RequestParam(name = "name", defaultValue = "jarry") String name){

        return HTTPResponse.returnSuccess("Post: " + name);
    }
    @PutMapping({"/test"})
    public HTTPResponse putTest(@RequestParam(name = "name", defaultValue = "jarry") String name){

        return HTTPResponse.returnSuccess("Put: " + name);
    }
    @DeleteMapping({"/test"})
    public HTTPResponse deleteTest(@RequestParam(name = "name", defaultValue = "jarry") String name){

        return HTTPResponse.returnSuccess("Delete: " + (name));
    }

}

