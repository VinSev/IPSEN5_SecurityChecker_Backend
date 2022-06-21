package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ScanDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CreateScanService {
    @Autowired
    private ScanCategoryService scanCategoryService;
    @Autowired
    private ScanDao scanDao;

    public Scan createNewScan(Scan currentScan) {
        return this.scanDao.create(fillNewScan(currentScan));
    }

    private Scan fillNewScan(Scan currentScan) {
        List<ScanCategory> completedScans = this.scanCategoryService.fillAllScanCategories(currentScan);
        currentScan.setCategories(completedScans);
        return currentScan;

    }
}
