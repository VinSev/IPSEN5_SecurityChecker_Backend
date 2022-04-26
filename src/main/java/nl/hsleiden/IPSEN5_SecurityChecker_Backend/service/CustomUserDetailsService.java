package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.UserDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Role;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByEmail(username);
    }

    @Transactional
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = this.userDao.get(email);
        Collection<GrantedAuthority> authorities = getAuthorities(user);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    private Collection<GrantedAuthority> getAuthorities(User user){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : user.getRole()){
            authorities.add(new SimpleGrantedAuthority(role.getRoleName().toUpperCase()));
        }
        return authorities;
    }
}
