package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository.UserRepository;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao implements Dao<User, String> {
    @Autowired
    private UserRepository repository;

    @Override
    public List<User> getAll() {
        return this.repository.findAll();
    }

    @Override
    public User get(String id) {
        return this.repository.getById(id);
    }

    @Override
    public User create(User user) {
        return this.repository.save(user);
    }

    @Override
    public void update(User user) {
        this.repository.save(user);
    }

    @Override
    public void delete(User user) {
        this.repository.delete(user);
    }

    public boolean exists(String email) {
        return this.repository.existsById(email);
    }
}
