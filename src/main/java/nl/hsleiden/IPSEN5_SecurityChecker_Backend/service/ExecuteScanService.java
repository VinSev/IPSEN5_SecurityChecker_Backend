package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ApiScanDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanCategory;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ApiScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.SecurityAlert;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.AbstractApiScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.certificate.CertificateAbstractApiScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.header.HeaderAbstractApiScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.seo.SeoScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.utility.ActiveScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.wordpress.WordPressVulnerability;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExecuteScanService {
    private static final int PASSED_NUMBER = (int) 5.5;

    @Autowired
    private ApiScanDao apiScanDao;

    private static final List<AbstractApiScan> listOFAbstractApiScan = setListOFAbstractApiScan();

    public static List<AbstractApiScan> setListOFAbstractApiScan() {
        ArrayList<AbstractApiScan> apiScanArrayList = new ArrayList<>();
        apiScanArrayList.add(new HeaderAbstractApiScan());
        apiScanArrayList.add(new CertificateAbstractApiScan());
        apiScanArrayList.add(new SeoScan());
        apiScanArrayList.add(new WordPressVulnerability());
        apiScanArrayList.add(new ActiveScan()); // The scan has to be activated Differently
        return apiScanArrayList;

    }

    public void addApiScan(AbstractApiScan newObject) {
        listOFAbstractApiScan.add(newObject);
    }

    public void removeApiScan(AbstractApiScan newObject) {
        listOFAbstractApiScan.remove(newObject);
    }

    public ArrayList<SecurityAlert> setScanCategoriesToScanAlerts(List<ScanCategory> executedScans) {
        ArrayList<SecurityAlert> securedAlerts = new ArrayList<>();
        for (ScanCategory scanCategory : executedScans) {
            boolean isPassed = scanCategory.getGrade() < PASSED_NUMBER;
            securedAlerts.add(new SecurityAlert(scanCategory.getGrade(), scanCategory.getSubScan().getName(), isPassed, scanCategory.getResult()));
        }
        return securedAlerts;
    }

    public Map<String, AbstractApiScan> combineSubScanToApiScan(List<ApiScan> scans) {
        Map<String, AbstractApiScan> MapSubScan = new HashMap<>();
        int scanNumber = 0;
        for (ApiScan apiScan : scans) {
            if (scanNumber == listOFAbstractApiScan.size()) {
                break;
            }
            MapSubScan.put(apiScan.getName(), listOFAbstractApiScan.get(scanNumber));
            scanNumber++;
        }
        return MapSubScan;
    }

    //  Tod0  ArrayList<ScanAlert> List<ScanCategory>
    public List<ScanCategory> executeScans(Map<String, AbstractApiScan> scanToApi, Scan scan, List<ApiScan> apiScans) {
        List<ScanCategory> executedCategoryScans = new ArrayList<>();
        try {
//            Go trough all scans
            for (ApiScan apiScan : apiScans) {
//                Go trou all ApiClass scans
                for (String key : scanToApi.keySet()) {
//                    Check if the scan name is equal to the key of the ApiScan
                    if (apiScan.getName().equals(key)) {
//                        Make it into a Category
                        ScanCategory scanCategory = new ScanCategory(scan, 0, apiScan);
//                       Execute it for a grade
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

    public List<ScanCategory> getResultsFromScans(List<ScanCategory> executedScans, Map<String, AbstractApiScan> scanToApi) {
        try {
            for (String key : scanToApi.keySet()) {
                JSONObject result = scanToApi.get(key).getResult();
//            Go trough all scans
                for (ScanCategory scanCategory : executedScans) {
                    int grade = Integer.parseInt(result.get("grade").toString());
                    scanCategory.setGrade(grade);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return executedScans;
    }

}
