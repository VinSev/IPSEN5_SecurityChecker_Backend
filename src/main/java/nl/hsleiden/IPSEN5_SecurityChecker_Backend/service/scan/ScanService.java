package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.scan;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanAlert;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.AbstractScan;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ScanService {

    public List<ScanAlert> scan(String website, ScanReport scanReport, AbstractScan scan) throws IOException, InterruptedException {
        scan.execute(scanReport, website);
        return scan.getResult();
    }
}
