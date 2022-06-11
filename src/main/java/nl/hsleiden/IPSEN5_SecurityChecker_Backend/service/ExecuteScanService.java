package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanCategory;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.SubScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.ApiScan;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExecuteScanService {

    private List<ApiScan> listOFApiScan;

    public void addApiScan(ApiScan newObject) {
        this.listOFApiScan.add(newObject);
    }

    public void removeApiScan(ApiScan newObject) {
        this.listOFApiScan.remove(newObject);
    }


    public Map<String, ApiScan> combineSubScanToApiScan(List<SubScan> scans) {
        Map<String, ApiScan> MapSubScan = new HashMap<>();
        int scanNumber = 0;
        for (SubScan subScan : scans) {
            MapSubScan.put(subScan.getName(), this.listOFApiScan.get(scanNumber));
            scanNumber++;
        }
        return MapSubScan;
    }

    public List<ScanCategory> executeScans(Map<String, ApiScan> scanToApi, Scan scan, List<SubScan> subScans) {
        List<ScanCategory> executedCategoryScans = new ArrayList<>();
        try {
//            Go trough all scans
            for (SubScan subScan : subScans) {
//                Go trou all ApiClass scans
                for (String key : scanToApi.keySet()) {
//                    Check if the scan name is equal to the key of the ApiScan
                    if (subScan.getName().equals(key)) {
//                        Make it into a Category
                        ScanCategory scanCategory = new ScanCategory(scan, 0, subScan);
//                       Execute it  for a grade
                        scanToApi.get(key).execute(scanCategory, scan.getUrl());
                    }
                }
            }
           executedCategoryScans = this.getResultsFromScans(executedCategoryScans, scanToApi);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return executedCategoryScans;
    }

    public List<ScanCategory> getResultsFromScans(List<ScanCategory> executedScans, Map<String, ApiScan> scanToApi) {
        try {
            for (String key : scanToApi.keySet()) {
                JSONObject result = scanToApi.get(key).getResult();
//            Go trough all scans
                for (ScanCategory scanCategory : executedScans) {
                   scanCategory.setGrade(Integer.parseInt(result.get("grade").toString()));
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return executedScans;
    }


}
