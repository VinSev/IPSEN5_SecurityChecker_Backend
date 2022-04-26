package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.RoleDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public Role create(Role role) {
        return roleDao.create(role);
    }
}
