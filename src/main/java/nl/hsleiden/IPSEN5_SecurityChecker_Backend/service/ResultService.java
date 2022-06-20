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
    @Autowired
    private ScanDao scanDao;

    public ScanResult create(ScanResult scanResult) {
        return resultDao.create(scanResult);
    }

    public Scan createNewResult(Scan currentScan) {
        ScanResult newScanResult = fillNewScanResult(currentScan);
        currentScan.setScanResult(newScanResult);
        return this.scanDao.create(currentScan);
    }

    private ScanResult fillNewScanResult(Scan currentScan) {
        ScanResult newScanResult = new ScanResult();
        List<ScanCategory> completedScans =
                this.scanCategoryService.fillAllScanCategories(currentScan);
        newScanResult.setScanResults(completedScans);
//        currentScan.(LocalDateTime.now().toString());
        return newScanResult;

    }
}
