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

    public Report create(String website) {
        ScanUserService scanUserService = new ScanUserService();
        ScanUser scanUser = new ScanUser();
        scanUser.setWebsite(website);
        scanUser = scanUserService.create(scanUser);

        Report report = new Report();
        report.setScanUser(scanUser);

        List<ScanReport> scanReports = new ArrayList<>();
        scanReports.add(createScanReport("Header", "/scan/header"));
        scanReports.add(createScanReport("Certificate", "/scan/certificate"));
        scanReports.add(createScanReport("Vulnerability", "/scan/vulnerability"));
        scanReports.add(createScanReport("XSS & Injection", "/scan/xss-and-injection"));
        scanReports.add(createScanReport("Seo", "/scan/seo"));
        report.setScanReports(scanReports);

        return report;
    }

    private ScanReport createScanReport(String title, String endpoint) {
        ScanReport scanReport = new ScanReport();
        scanReport.setTitle(title);
        scanReport.setEndpoint(endpoint);
        return scanReport;
    }

    public void update(Report report) {
        this.reportDao.update(report);
    }

    public void delete(Report report) {
        this.reportDao.delete(report);
    }
}
