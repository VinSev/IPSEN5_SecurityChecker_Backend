package nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan;

import org.json.JSONObject;

import java.io.IOException;

public abstract class AbstractApiScan {

    private ScanCategory scanCategory;
    private String website;

    public void execute(ScanCategory scanCategory, String website) throws IOException, InterruptedException {
        this.scanCategory = scanCategory;
        this.website = website;
    }

    public JSONObject getResult() throws IOException, InterruptedException {
        return new JSONObject(scanCategory.getResult());
    }

    public void setResult(JSONObject result) {
        scanCategory.setResult(result.toString());
    }

    public String getWebsite() {
        return website;
    }
}
