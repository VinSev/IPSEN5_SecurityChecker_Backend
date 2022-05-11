package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Result;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScanService {
    private Scan[] scans;

    @Autowired
    private ResultService resultService;

    public ScanService(ResultService resultService) {
        this.resultService = resultService;
    }

    private Result startScan(User user, String url) {
         Result newResult = this.resultService.createNew(user, url);
         Result finishedResult = ScanProcess(newResult);
         return  finishedResult;
    }

    private Result ScanProcess(Result result){
        for(Scan scan: scans){
             result = addScanToResult(result);
        }
        return result;
    }

    private Result addScanToResult(Result currentResult) {
        currentResult.setScansDone(currentResult.getScansDone() + 1);
        return currentResult;
    }

    private String updateScanStatus(Scan finishedScan){
      return "Scan name";
    }

}
