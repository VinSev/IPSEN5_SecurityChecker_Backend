package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.SubScanDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanCategory;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.SubScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.header.CertificateScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.header.HeaderScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubScanService {
    @Autowired
    private SubScanDao subScanDao;

    @Autowired
    private HeaderScan headerScan;

    @Autowired
    private CertificateScan certificateScan;

    public List<SubScan> giveAllPossibleSubScans() {
        List<SubScan> scans = this.subScanDao.getAll();
        Map<String, Object> MapSubScan = new HashMap<>();
        for (SubScan scan : scans
        ) {
            MapSubScan.put(scan.getName(),)

        }
        return MapSubScan;
    }

    public ScanCategory getResultOfSubScan(SubScan subScan, Scan scan) {
        ScanCategory scanCategory = new ScanCategory(scan, 0, subScan);
        try {
            switch (subScan.getName()) {
                case "header":
                    this.headerScan.execute(new ScanCategory(scan, 0, subScan), scan.getUrl());
                    break;
                case "certificate":
                    this.certificateScan.execute(new ScanCategory(scan, 0, subScan), scan.getUrl());
                    break;
                case "database":
                    this.headerScan.execute(new ScanCategory(scan, 0, subScan), scan.getUrl());
                    break;
                case "XSS":
                    this.headerScan.execute(new ScanCategory(scan, 0, subScan), scan.getUrl());
                    break;
                case "":
                    this.headerScan.execute(new ScanCategory(scan, 0, subScan), scan.getUrl());
                    break;
                case "wordpress":
                    this.headerScan.execute(new ScanCategory(scan, 0, subScan), scan.getUrl());
                    break;
                default:
                    throw new Exception("name not known");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return scanCategory
    }
}
