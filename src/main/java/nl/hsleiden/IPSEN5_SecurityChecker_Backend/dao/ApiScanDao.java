package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository.SubScanRepository;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ApiScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApiScanDao implements Dao<ApiScan, String> {

    @Autowired
    private SubScanRepository repository;

    @Override
    public List<ApiScan> getAll() {
        return this.repository.findAll();
    }

    @Override
    public ApiScan get(String id) {
        return this.repository.getById(id);
    }

    @Override
    public ApiScan create(ApiScan apiScan) {
        return this.repository.save(apiScan);
    }

    @Override
    public void update(ApiScan apiScan) {
        this.repository.save(apiScan);
    }

    @Override
    public void delete(ApiScan apiScan) {
        this.repository.delete(apiScan);
    }
}
