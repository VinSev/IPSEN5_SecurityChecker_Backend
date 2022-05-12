package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository.ScanRepository;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScanDao implements Dao<Scan, Long> {
    @Autowired
    private ScanRepository repository;

    @Override
    public List<Scan> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Scan get(Long id) {
        return this.repository.getById(id);
    }

    @Override
    public Scan create(Scan scan) {return this.repository.save(scan);}

    @Override
    public void update(Scan scan) {
        this.repository.save(scan);
    }

    @Override
    public void delete(Scan scan) {
        this.repository.delete(scan);
    }
}
