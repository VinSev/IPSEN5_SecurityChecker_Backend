package nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import org.json.JSONObject;

import java.io.IOException;

public abstract class AbstractScan {

    private ScanReport scanReport;
    private String website;

    public void execute(ScanReport scanReport, String website) throws IOException, InterruptedException {
        this.scanReport = scanReport;
        this.website = website;
    }

    public JSONObject getResult() throws IOException, InterruptedException {
        return scanReport.getResult();
    }

    public void setResult(JSONObject result) {
        scanReport.setResult(result);
    }

    public String getWebsite() {
        return website;
    }
}
