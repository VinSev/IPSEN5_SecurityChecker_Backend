package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository.SubScanRepository;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.SubScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubScanDao implements Dao<SubScan, String> {

    @Autowired
    private SubScanRepository repository;

    @Override
    public List<SubScan> getAll() {
        return this.repository.findAll();
    }

    @Override
    public SubScan get(String id) {
        return this.repository.getById(id);
    }

    @Override
    public SubScan create(SubScan subScan) {
        return this.repository.save(subScan);
    }

    @Override
    public void update(SubScan subScan) {
        this.repository.save(subScan);
    }

    @Override
    public void delete(SubScan subScan) {
        this.repository.delete(subScan);
    }
}
