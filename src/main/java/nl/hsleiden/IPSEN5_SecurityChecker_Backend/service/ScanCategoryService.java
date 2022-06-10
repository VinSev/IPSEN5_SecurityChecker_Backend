package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanCategory;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.SubScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
@Service
public class ScanCategoryService {
    @Autowired
    private SubScanService subScanService;

    public List<ScanCategory> fillAllScanCategories(Scan scan){
        List<SubScan> listOfScans = this.subScanService.giveAllPossibleSubScans();
        Dictionary<SubScan,>
        ArrayList<ScanCategory> listOfFilledScans= new ArrayList<>();
        for (SubScan subScan : listOfScans) {
            listOfFilledScans.add(addScanCategory(subScan,scan));
        }
        return listOfFilledScans;

        }

    public ScanCategory addScanCategory(SubScan subScan, Scan scan) {
            return this.subScanService.getResultOfSubScan(subScan, scan);
    }
}
