package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanCategory;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ApiScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScanCategoryService {
    @Autowired
    private SubScanService subScanService;

    public ArrayList<ScanCategory> fillAllScanCategories(Scan scan) {
        List<ApiScan> listOfScans = this.subScanService.giveAllPossibleSubScans();
        return this.fillScanCategory(listOfScans, scan);
    }

    public ArrayList<ScanCategory> fillScanCategory(List<ApiScan> apiScans, Scan scan) {
        return (ArrayList<ScanCategory>) this.subScanService.getResultOfSubScan(apiScans, scan);
    }
}
