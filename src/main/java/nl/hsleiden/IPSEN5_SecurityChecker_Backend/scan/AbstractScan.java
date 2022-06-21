package nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanAlert;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public abstract class AbstractScan {

    private ScanReport scanReport;
    private JSONObject temporaryResult;
    private String website;

    public void execute(ScanReport scanReport, String website) throws IOException, InterruptedException {
        this.scanReport = scanReport;
        this.website = website;
    }

    public List<ScanAlert> getResult() throws IOException, InterruptedException {
        return scanReport.getResult();
    }

    public void setResult(List<ScanAlert> result) {
        scanReport.setResult(result);
    }

    public JSONObject getTemporaryResult() {
        return temporaryResult;
    }

    public void setTemporaryResult(JSONObject temporaryResult) {
        this.temporaryResult = temporaryResult;
    }

    public void setGrade(int grade) {
        this.scanReport.setGrade(grade);
    }

    public String getWebsite() {
        return website;
    }
}
