package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.RoleDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.UserDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Role;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        this.roleDao.create(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created account");
        this.roleDao.create(userRole);

        User adminUser = new User();
        adminUser.setEmail("admin@admin.com");
        adminUser.setPassword(getEncodedPassword("admin"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        this.userDao.create(adminUser);

        User normalUser = new User();
        normalUser.setEmail("user");
        normalUser.setPassword(getEncodedPassword("user"));
        Set<Role> normalRoles = new HashSet<>();
        normalRoles.add(userRole);
        normalUser.setRole(normalRoles);
        this.userDao.create(normalUser);
    }

    public User register(User user) {
        if(emailAlreadyExists(user.getEmail())) {
            return null;
        }

        Role role = this.roleDao.get("User");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setPassword(getEncodedPassword(user.getPassword()));

        return this.userDao.create(user);
    }

    public String getEncodedPassword(String password) {
        return this.passwordEncoder.encode(password);
    }

    private boolean emailAlreadyExists(String email) {
        return this.userDao.exists(email);
    }
}
