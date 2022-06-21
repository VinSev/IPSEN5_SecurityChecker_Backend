package nl.hsleiden.IPSEN5_SecurityChecker_Backend.utility;


import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanAlert;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.AbstractScan;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.zaproxy.clientapi.core.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Service
public class ActiveScan extends AbstractScan {

    private static final int ZAP_PORT = 8081;
    private static final String ZAP_API_KEY = "kie7nrse6gi4uvgoof8mm68289";
    private static final String ZAP_ADDRESS = "localhost";
    private ArrayList<ScanAlert> scans = new ArrayList<>();

    @Override
    public void execute(ScanReport scanReport, String website) throws IOException, InterruptedException {
        super.execute(scanReport, website);

    }

    @Override
    public List<ScanAlert> getResult() throws IOException, InterruptedException {
        return super.getResult();
    }

    public static ArrayList<ScanAlert> spiderSearch(String target) {

        ClientApi api = new ClientApi(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY);

        try {


            System.out.println("Ajax Spider target : " + target);
            ApiResponse resp = api.ajaxSpider.scan(target, null, null, null);
            String status;

            long startTime = System.currentTimeMillis();
            long timeout = TimeUnit.MINUTES.toMillis(2);

            while (true) {
                Thread.sleep(2000);
                status = (((ApiResponseElement) api.ajaxSpider.status()).getValue());
                System.out.println("Spider status : " + status);
                if (!("stopped".equals(status)) || (System.currentTimeMillis() - startTime) < timeout) {
                    break;
                }
            }
            System.out.println("Ajax Spider completed");


            System.out.println("Active Scanning target : " + target);
            ApiResponse resp2 = api.ascan.scan(target, "True", "False", null, null, null);
            String scanid;
            int progress;




            scanid = ((ApiResponseElement) resp2).getValue();

            while (true) {
                Thread.sleep(5000);
                progress =
                        Integer.parseInt(
                                ((ApiResponseElement) api.ascan.status(scanid)).getValue());
                System.out.println("Active Scan progress : " + progress + "%");
                if (progress >= 100) {
                    break;
                }
            }

            System.out.println("Active Scan complete");


            byte[] reporto = api.core.jsonreport();
            JSONObject jsonnn = new JSONObject(new String(reporto));
            JSONArray jsonarray = jsonnn.getJSONArray("site");
            JSONObject json2 = new JSONObject(jsonarray.get(0).toString());
            JSONArray jsonar2 = json2.getJSONArray("alerts");


            ArrayList<Integer> scores = new ArrayList<>();

            ArrayList<ScanAlert> scanAlerts = new ArrayList<>();
            for (int i = 0; i < jsonar2.length(); i++) {
                JSONObject json3 = new JSONObject(jsonar2.get(i).toString());
                HashMap<String, Integer> warnings = new HashMap<String, Integer>();
                warnings.put("Informational",1);
                warnings.put("Low",3);
                warnings.put("Medium",5);
                warnings.put("High",10);




                String scanScore = json3.get("riskdesc").toString().split(" ")[0];
                String titel  = json3.get("name").toString();
                Boolean geslaagd = true;
                String uitleg = json3.get("desc").toString();


                scores.add(warnings.get(scanScore));

                if (warnings.get(scanScore) < 5){
                    geslaagd = false;
                }

                ScanAlert currentAlert = new ScanAlert(titel,geslaagd,uitleg);
                scanAlerts.add(currentAlert);
            }



            OptionalDouble startGrade = scores.stream().mapToDouble(a -> a).average();
            Double grade = startGrade.getAsDouble();

            int avgGrade = grade.intValue();





           return scanAlerts;

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
      ArrayList<ScanAlert> failedScans = new ArrayList<>();
        return failedScans;
    }
}
