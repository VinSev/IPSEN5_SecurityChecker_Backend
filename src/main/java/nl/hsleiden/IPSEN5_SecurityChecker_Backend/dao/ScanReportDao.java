package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository.ScanReportRepository;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScanReportDao implements Dao<ScanReport, Long> {
    @Autowired
    private ScanReportRepository repository;

    @Override
    public List<ScanReport> getAll() {
        return this.repository.findAll();
    }

    @Override
    public ScanReport get(Long id) {
        return this.repository.getById(id);
    }

    @Override
    public ScanReport create(ScanReport scanReport) {
        return this.repository.save(scanReport);
    }

    @Override
    public void update(ScanReport scanReport) {
        this.repository.save(scanReport);
    }

    @Override
    public void delete(ScanReport scanReport) {
        this.repository.delete(scanReport);
    }
}
