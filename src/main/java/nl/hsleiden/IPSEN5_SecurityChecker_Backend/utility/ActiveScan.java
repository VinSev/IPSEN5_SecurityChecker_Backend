package nl.hsleiden.IPSEN5_SecurityChecker_Backend.utility;


import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.SecurityAlert;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.zaproxy.clientapi.core.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Service
public class ActiveScan {

    private static final int ZAP_PORT = 8081;
    private static final String ZAP_API_KEY = "kie7nrse6gi4uvgoof8mm68289";
    private static final String ZAP_ADDRESS = "localhost";
    private static final String TARGET = "https://webshop-vacations.herokuapp.com/";



    public static ArrayList<SecurityAlert> spiderSearch() {

        ClientApi api = new ClientApi(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY);

        try {


            System.out.println("Ajax Spider target : " + TARGET);
            ApiResponse resp = api.ajaxSpider.scan(TARGET, null, null, null);
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


            System.out.println("Active Scanning target : " + TARGET);
            ApiResponse resp2 = api.ascan.scan(TARGET, "True", "False", null, null, null);
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

            System.out.println("Alerts:");

            byte[] reporto = api.core.jsonreport();


            JSONObject jsonnn = new JSONObject(new String(reporto));



            JSONArray jsonarray = jsonnn.getJSONArray("site");


            JSONObject json2 = new JSONObject(jsonarray.get(0).toString());
            JSONArray jsonar2 = json2.getJSONArray("alerts");
            System.out.println(jsonar2);


            ArrayList<SecurityAlert> scanAlerts = new ArrayList<>();
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


                if (warnings.get(scanScore) < 5){
                    geslaagd = false;
                }

                SecurityAlert currentAlert = new SecurityAlert(warnings.get(scanScore),titel,geslaagd,"hellokitty");
                scanAlerts.add(currentAlert);
            }

            scanAlerts.forEach(scan -> {
                System.out.println(scan.getTitel());
            });


           return scanAlerts;

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
      ArrayList<SecurityAlert> failedScans = new ArrayList<>();
        return failedScans;
    }
}
