package nl.hsleiden.IPSEN5_SecurityChecker_Backend.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.zaproxy.clientapi.core.*;

import javax.swing.text.html.HTMLDocument;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ActiveScan {

    private static final int ZAP_PORT = 8081;
    private static final String ZAP_API_KEY = "kie7nrse6gi4uvgoof8mm68289";
    private static final String ZAP_ADDRESS = "localhost";
    private static final String TARGET = "https://webshop-vacations.herokuapp.com/";

    public static void attackScan() {

        ClientApi api = new ClientApi(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY);

        try {

            System.out.println("Active Scanning target : " + TARGET);
            ApiResponse resp = api.ascan.scan(TARGET, "True", "False", null, null, null);
            String scanid;
            int progress;

            // The scan now returns a scan id to support concurrent scanning
            scanid = ((ApiResponseElement) resp).getValue();
            // Poll the status until it completes
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
            // Print vulnerabilities found by the scanning
            System.out.println("Alerts:");
            System.out.println(new String(api.core.xmlreport(), StandardCharsets.UTF_8));

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void spiderSearch() {

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


            System.out.println(jsonnn);
            JSONArray jsonarray = jsonnn.getJSONArray("site");
            Object jsonobj = jsonarray.get(0);
            System.out.println(jsonarray);

            ObjectMapper oMapper = new ObjectMapper();

//            for (int j = 0; j < jsonarray.length(); j++) {
//                System.out.println("test");
//                for (int k = 0; k < jsonarray.getJSONArray(j).length(); k++) {
//                    String country = jsonarray.getJSONArray(j).getJSONObject(k).getString("riskdesc");
//                    Integer count = jsonarray.getJSONArray(j).getJSONObject(k).getInt("name");
//                    System.out.println(count);
//                    cargo.put(country, count);
//                }
//            }






//
//            FileWriter xmlFile = new FileWriter("report.txt");
//            xmlFile.write(new String(api.core.xmlreport(), StandardCharsets.UTF_8));
//


        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
