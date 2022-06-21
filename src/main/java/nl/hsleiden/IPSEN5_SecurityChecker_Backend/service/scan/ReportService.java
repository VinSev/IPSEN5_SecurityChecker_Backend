package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.scan;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ReportDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.Report;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportDao reportDao;

    public List<Report> getAll() {
        return this.reportDao.getAll();
    }

    public Report get(Long id) {
        return this.reportDao.get(id);
    }

    public Report create(Report report) {
        return this.reportDao.create(report);
    }

    public void update(Report report) {
        this.reportDao.update(report);
    }

    public void delete(Report report) {
        this.reportDao.delete(report);
    }
}
