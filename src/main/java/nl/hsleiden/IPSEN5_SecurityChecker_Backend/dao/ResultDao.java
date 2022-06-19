package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao;


import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository.ResultRepository;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultDao implements Dao<ScanResult, Long> {

    @Autowired
    private ResultRepository repository;

    @Override
    public List<ScanResult> getAll() {
        return this.repository.findAll();
    }

    @Override
    public ScanResult get(Long id) {
        return this.repository.getById(id);
    }

    @Override
    public ScanResult create(ScanResult scanResult) {
        return this.repository.save(scanResult);
    }

    @Override
    public void update(ScanResult scanResult) {
        this.repository.save(scanResult);
    }

    @Override
    public void delete(ScanResult scanResult) {
        this.repository.delete(scanResult);
    }

}
