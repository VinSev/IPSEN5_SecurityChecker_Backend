package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ScanDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanResult;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ScanService {

    private List<Scan> scans = new ArrayList<>();

    @Autowired
    private ResultService resultService;

    @Autowired
    private ScanDao scanDao;

    public ScanService(ResultService resultService, ScanDao scanDao) {
        this.scanDao = scanDao;
        this.scans = this.scanDao.getAll();
        this.resultService = resultService;
    }

    private ScanResult startScan(User user, String url) {
        Scan lilScan = new Scan(user.getEmail(),true, url, null, "today");
         ScanResult newScanResult = this.resultService.createNewResult(lilScan);
        return ScanProcess(newScanResult);
    }

    private ScanResult ScanProcess(ScanResult scanResult){
        for(Scan scan: scans){
            addScanToResult(scanResult,scan);
        }
        return scanResult;
    }

    private ScanResult addScanToResult(ScanResult currentScanResult, Scan scan) {
//        ScanResult_TEST scanResults = new ScanResult_TEST( scan,0, scan.getLink(), "ScanName");
//        currentScanResult.setScanResults(currentScanResult.getScanResults());
//                .add(scanResults));  Geeft boolean terug ??
        return currentScanResult;
    }

    private int calculateGrade(HashMap<String, String> scanComponents) {
        int passed = 0;
        List<String> values = (List<String>) scanComponents.values();
        for(String value: values){
            if(Integer.parseInt(value) > 5.5){
                passed+=1;
            }
        }
         return scanComponents.size()/ passed * scanComponents.size();
    }

    private HashMap<String, String> getScanResult(String link, String url) {
//        request
        String response = "";
        String[] grades  = response.split(":");
        HashMap<String,String> results = new HashMap<>();
        return  results;
    }

    private String updateScanStatus(Scan finishedScan){
      return "Scan: 4";
    }

}
