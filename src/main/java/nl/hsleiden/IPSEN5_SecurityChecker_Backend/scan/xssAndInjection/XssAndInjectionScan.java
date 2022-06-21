package nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.xssAndInjection;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanAlert;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.AbstractScan;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class XssAndInjectionScan extends AbstractScan {

    @Override
    public void execute(ScanReport scanReport, String website) throws IOException, InterruptedException {

    }

    @Override
    public List<ScanAlert> getResult() throws IOException, InterruptedException {
        return super.getResult();
    }
}
