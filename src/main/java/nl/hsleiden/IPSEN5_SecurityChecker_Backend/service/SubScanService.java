package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.SubScanDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanCategory;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.SubScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.ApiScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.header.CertificateApiScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.header.HeaderApiScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubScanService {
    @Autowired
    private SubScanDao subScanDao;

    @Autowired
    private HeaderApiScan headerScan;

    @Autowired
    private CertificateApiScan certificateScan;

    @Autowired
    private ExecuteScanService excecuteScanService

    public List<SubScan> giveAllPossibleSubScans() {
        return this.subScanDao.getAll();
    }

    public ScanCategory getResultOfSubScan(SubScan subScan, Scan scan) {
        ScanCategory scanCategory = new ScanCategory(scan, 0, subScan);
        try {
            this.excecuteScanService.ExecuteAllApiScans(scan);
            return scanCategory;
//            TODO return a HAshmap with andswer and Scan CAtegirty
            System.out.println("hi");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return scanCategory;
    }
}
