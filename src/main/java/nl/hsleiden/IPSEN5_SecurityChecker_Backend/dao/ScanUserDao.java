package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository.ScanUserRepository;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScanUserDao implements Dao<ScanUser, Long> {
    @Autowired
    private ScanUserRepository repository;

    @Override
    public List<ScanUser> getAll() {
        return this.repository.findAll();
    }

    @Override
    public ScanUser get(Long id) {
        return this.repository.getById(id);
    }

    @Override
    public ScanUser create(ScanUser scanUser) {
        return this.repository.save(scanUser);
    }

    @Override
    public void update(ScanUser scanUser) {
        this.repository.save(scanUser);
    }

    @Override
    public void delete(ScanUser scanUser) {
        this.repository.delete(scanUser);
    }
}
