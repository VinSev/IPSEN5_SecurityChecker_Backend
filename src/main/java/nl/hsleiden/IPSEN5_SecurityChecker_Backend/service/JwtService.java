package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.UserDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.UserRequest;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.JwtResponse;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.User;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.UserResponse;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JwtService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDao userDao;

    public JwtResponse createJwtToken(UserRequest userRequest) {
        String email = userRequest.getEmail();

        UserDetails userDetails = loadUserByEmail(email);
        String newGeneratedToken = jwtUtil.generateToken(userDetails.getUsername());

        User user = userDao.get(email);
        List<String> roles = new ArrayList<>();
        user.getRole().forEach(role -> roles.add(role.getRoleName()));
        return new JwtResponse(new UserResponse(user.getEmail(), roles), newGeneratedToken);
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userDao.get(email);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
    }

    private Set<? extends SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }


}
