package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository.RoleRepository;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleDao implements Dao<Role, String> {
    @Autowired
    private RoleRepository repository;

    @Override
    public List<Role> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Role get(String id) {
        return this.repository.getById(id);
    }

    @Override
    public Role create(Role role) {
       return this.repository.save(role);
    }

    @Override
    public void update(Role role) {
        this.repository.save(role);
    }

    @Override
    public void delete(Role role) {
        this.repository.delete(role);
    }
}
