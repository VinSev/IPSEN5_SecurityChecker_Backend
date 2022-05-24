package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository.ScanCategoryRepository;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScanCategoryDao implements Dao<ScanCategory, String> {
    @Autowired
    private ScanCategoryRepository repository;

    @Override
    public List<ScanCategory> getAll() {
        return this.repository.findAll();
    }

    @Override
    public ScanCategory get(String id) {
        return this.repository.getById(id);
    }

    @Override
    public ScanCategory create(ScanCategory role) {
        return this.repository.save(role);
    }

    @Override
    public void update(ScanCategory role) {
        this.repository.save(role);
    }

    @Override
    public void delete(ScanCategory role) {
        this.repository.delete(role);
    }
}
