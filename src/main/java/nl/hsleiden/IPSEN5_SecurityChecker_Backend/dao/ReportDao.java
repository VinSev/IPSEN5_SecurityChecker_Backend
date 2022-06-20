package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository.ReportRepository;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportDao implements Dao<Report, Long> {
    @Autowired
    private ReportRepository repository;

    @Override
    public List<Report> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Report get(Long id) {
        return this.repository.getById(id);
    }

    @Override
    public Report create(Report report) {
        return this.repository.save(report);
    }

    @Override
    public void update(Report report) {
        this.repository.save(report);
    }

    @Override
    public void delete(Report report) {
        this.repository.delete(report);
    }
}
