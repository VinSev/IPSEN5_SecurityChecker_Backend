package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.annotation.IsAdmin;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Role;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/roles")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

    @IsAdmin
    @PostMapping()
    public Role create(@RequestBody Role role) {
        return roleService.create(role);
    }
}
