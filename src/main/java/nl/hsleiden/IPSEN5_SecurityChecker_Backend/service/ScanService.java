package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ScanDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Result;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanResult;
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

    private Result startScan(User user, String url) {
         Result newResult = this.resultService.createNew(url);
         Result finishedResult = ScanProcess(newResult);
         return  finishedResult;
    }

    private Result ScanProcess(Result result){
        for(Scan scan: scans){
            addScanToResult(result,scan);
        }
        return result;
    }

    private Result addScanToResult(Result currentResult, Scan scan) {
        HashMap<String,String> scanComponents = getScanResult(scan.getLink(), currentResult.getUrl());
        int grade = calculateGrade(scanComponents);
        ScanResult scanResults = new ScanResult(scan, currentResult,grade, scanComponents);
        currentResult.setScanResults(currentResult.getScanResults());
//                .add(scanResults));  Geeft boolean terug ??
        return currentResult;
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
