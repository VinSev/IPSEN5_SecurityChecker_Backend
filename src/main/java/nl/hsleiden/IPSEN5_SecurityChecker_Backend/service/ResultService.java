package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ResultDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ScanDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ResultService {
    @Autowired
    private ResultDao resultDAO;
    @Autowired
    private ScanDao scanDAO;

    public ScanResult create(ScanResult scanResult) {
        return resultDAO.create(scanResult);
    }

    public ScanResult createNew(String url) {
        Scan currentScan = new Scan();
        currentScan.setLink(url);
//        Result newResult = new Result( LocalDateTime.now().toString(),url, new ArrayList<>(), false);
        ScanResult newScanResult = new ScanResult();
        currentScan.setScanResult(newScanResult);
        this.scanDAO.create(currentScan);
        return resultDAO.create(newScanResult);
    }
}
