package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.scan;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ScanReportDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.AbstractScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScanReportService {

    @Autowired
    private ScanReportDao scanReportDao;

    public List<ScanReport> getAll() {
        return this.scanReportDao.getAll();
    }

    public ScanReport get(Long id) {
        return this.scanReportDao.get(id);
    }

    public ScanReport create(ScanReport scanReport) {
        return this.scanReportDao.create(scanReport);
    }

    public void update(ScanReport scanReport) {
        this.scanReportDao.update(scanReport);
    }

    public void delete(ScanReport scanReport) {
        this.scanReportDao.delete(scanReport);
    }


    public ScanReport giveGradingToScanReport(ScanReport scanReport) {
        for (String key : scanReport.getResult().keySet()) {
            Object result = scanReport.getResult().get(key);
            ArrayList<String> optionsForResult = new ArrayList<>();
            optionsForResult.add("grade");
            optionsForResult.add("result");
            optionsForResult.add("score");
            System.out.println(result);
            JsonObject resultJson = new Gson().toJsonTree(result).getAsJsonObject();
            for (String gradeOption : optionsForResult) {
                AbstractScan scan = (AbstractScan) result;
                try {
                    String newGrade = scan.getResult().get(gradeOption).toString();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String grade = resultJson.get(gradeOption).toString();
                if (grade != null){
                    scanReport.setGrade(Integer.parseInt(grade));
                    break;
                }
            }
        }
        return scanReport;
    }
}


