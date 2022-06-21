package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ApiScanDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanCategory;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ApiScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.AbstractApiScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SubScanService {
    @Autowired
    private ApiScanDao apiScanDao;

    @Autowired
    private ExecuteScanService executeScanService;

    public List<ApiScan> giveAllPossibleSubScans() {
        return this.apiScanDao.getAll();
    }

    public List<ScanCategory> getResultOfSubScan(List<ApiScan> apiScans, Scan scan) {
        Map<String, AbstractApiScan> scanToApiMap = this.executeScanService.combineSubScanToApiScan(apiScans);
        List<ScanCategory> executedScans = this.executeScanService.executeScans(scanToApiMap, scan, apiScans);
        return executedScans;
    }
}
