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
    private ResultDao resultDAO;
    @Autowired
    private ScanDao scanDao;

    public ScanResult create(ScanResult scanResult) {
        return resultDAO.create(scanResult);
    }

    public ScanResult createNewResult(String url) {
        Scan currentScan = new Scan();
        currentScan.setLink(url);
        ScanResult newScanResult = fillNewScanResult(currentScan);
        newScanResult.setScan(currentScan);
        currentScan.setScanResult(newScanResult);
        this.scanDao.create(currentScan);
        return this.resultDAO.create(newScanResult);
    }

    private ScanResult fillNewScanResult(Scan currentScan) {
        ScanResult newScanResult = new ScanResult();
        List<ScanCategory> completedScans = this.scanCategoryService.fillAllScanCategories(currentScan);
        newScanResult.setScan(currentScan);
//        newScanResult.setScanCategories(completedScans);
//        newScanResult.setUpdatedDate(LocalDateTime.now().toString());
        return newScanResult;

    }
}
