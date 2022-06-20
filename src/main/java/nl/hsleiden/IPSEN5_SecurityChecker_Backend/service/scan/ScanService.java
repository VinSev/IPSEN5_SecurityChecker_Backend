package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.scan;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.AbstractScan;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ScanService {

    public ScanReport scan(String website, ScanReport scanReport, AbstractScan scan) throws IOException, InterruptedException {
        scan.execute(scanReport, website);
        scanReport.setResult(scan.getResult());
        return scanReport;
    }
}
