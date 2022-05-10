package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.User;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.UserRequest;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.UserResponse;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.HTTPResponse;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/register"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public UserResponse register(@RequestBody UserRequest userRequest) {
        User user = userService.register(new User(userRequest.getEmail(), userRequest.getPassword()));
        List<String> roles = new ArrayList<>();
        user.getRole().forEach(role -> roles.add(role.getRoleName()));
        return new UserResponse(user.getEmail(), roles);
    }

    @GetMapping({"/test"})
    public HTTPResponse getOrder(@RequestParam(name = "name", defaultValue = "jarry") String name){
        return HTTPResponse.returnSuccess("works");
    }

}
