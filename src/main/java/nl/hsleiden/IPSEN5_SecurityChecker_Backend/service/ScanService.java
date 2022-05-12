package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ScanDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScanService {
    @Autowired
    private ScanDao scanDao;

    public Scan saveScan(Scan scan){
        Scan newScan = new Scan(scan.getName(), scan.getEmail(), scan.getUrl());
        return this.scanDao.create(newScan);
    }
}
