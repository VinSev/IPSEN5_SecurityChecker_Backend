package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.SubScanDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanCategory;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.SubScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.ApiScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.header.CertificateApiScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.header.HeaderApiScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class SubScanService {
    @Autowired
    private SubScanDao subScanDao;

    @Autowired
    private ExecuteScanService executeScanService;

    public List<SubScan> giveAllPossibleSubScans() {
        return this.subScanDao.getAll();
    }

    public List<ScanCategory> getResultOfSubScan(List<SubScan> subScans, Scan scan) {
        Map<String, ApiScan> scanToApiMap = this.executeScanService.combineSubScanToApiScan(subScans);
        List<ScanCategory> exceutedScans = this.executeScanService.executeScans(scanToApiMap, scan, subScans);
        return exceutedScans;
    }
}
