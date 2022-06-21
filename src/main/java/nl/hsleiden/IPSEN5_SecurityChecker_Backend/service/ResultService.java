package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ResultDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ScanDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanCategory;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ResultService {
    @Autowired
    private ScanCategoryService scanCategoryService;
    @Autowired
    private ResultDao resultDao;

    public ScanResult create(ScanResult scanResult) {
        return resultDao.create(scanResult);
    }

    public ScanResult createNewResult(Scan currentScan) {
        ScanResult newScanResult = fillNewScanResult(currentScan);

        newScanResult.setScan((int) currentScan.getScanId());
        currentScan.setScanResult(newScanResult);
//        this.scanDao.create(currentScan);
        return this.resultDao.create(newScanResult);
    }

    private ScanResult fillNewScanResult(Scan currentScan) {
        ScanResult newScanResult = new ScanResult();
        List<ScanCategory> completedScans = this.scanCategoryService.fillAllScanCategories(currentScan);
        newScanResult.setScan((int) currentScan.getScanId());
        newScanResult.setScanResults(completedScans);

//       scan.setUpdatedDate(LocalDateTime.now().toString());
        return newScanResult;

    }
}
