package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanCategory;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.SubScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScanCategoryService {
    @Autowired
    private SubScanService subScanService;

    public ArrayList<ScanCategory> fillAllScanCategories(Scan scan) {
        List<SubScan> listOfScans = this.subScanService.giveAllPossibleSubScans();
        return this.fillScanCategory(listOfScans, scan);
    }

    public ArrayList<ScanCategory> fillScanCategory(List<SubScan> subScans, Scan scan) {
        return (ArrayList<ScanCategory>) this.subScanService.getResultOfSubScan(subScans, scan);
    }
}
