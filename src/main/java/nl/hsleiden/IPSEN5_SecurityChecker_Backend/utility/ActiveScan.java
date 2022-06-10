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

@Service
public class ActiveScan {

    private static final int ZAP_PORT = 8081;
    private static final String ZAP_API_KEY = "kie7nrse6gi4uvgoof8mm68289";
    private static final String ZAP_ADDRESS = "localhost";
    private static final String TARGET = "https://webshop-vacations.herokuapp.com/";

    public static void attackScan(){

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

    public static void spiderSearch () {

        ClientApi api = new ClientApi(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY);

        try {

            // Start spidering the target
            System.out.println("Ajax Spider target : " + TARGET);
            ApiResponse resp = api.ajaxSpider.scan(TARGET, null, null, null);
            String status;

            long startTime = System.currentTimeMillis();
            long timeout = TimeUnit.MINUTES.toMillis(2); // Two minutes in milli seconds
            // Loop until the ajax spider has finished or the timeout has exceeded
            while (true) {
                Thread.sleep(2000);
                status = (((ApiResponseElement) api.ajaxSpider.status()).getValue());
                System.out.println("Spider status : " + status);
                if (!("stopped".equals(status)) || (System.currentTimeMillis() - startTime) < timeout) {
                    break;
                }
            }
            System.out.println("Ajax Spider completed");
            // Perform additional operations with the Ajax Spider results
            List<ApiResponse> ajaxSpiderResponse = ((ApiResponseList) api.ajaxSpider.results("0", "10")).getItems();

            // TODO: Start scanning(passive/active scan) the application to find vulnerabilities
            System.out.println("Active Scanning target : " + TARGET);
            ApiResponse resp2 = api.ascan.scan(TARGET, "True", "False", null, null, null);
            int start = 0;
            int count = 5000;
            int alertCount = 0;
            String scanid;
            int progress;
            List<String> blackListPlugins = Arrays.asList("1000", "1025");


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

            System.out.println("Alerts:");
            System.out.println(new String(api.core.xmlreport(), StandardCharsets.UTF_8));
            byte[] reporto = api.core.jsonreport();
            System.out.println("YELLLING AT THE SUNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");

            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> map = mapper.readValue(reporto, Map.class);

            JSONObject jsonnn = new JSONObject(new String(reporto));


            System.out.println(jsonnn);
            System.out.println(jsonnn.getJSONArray("site"));

            JSONArray sites = new JSONArray(jsonnn.getJSONArray("site"));


            System.out.println(sites);


            int size = sites.length();

            for (int i = 0; i < size; i++) {
                JSONObject another_json_object = sites.getJSONObject(i);
                System.out.println(another_json_object);
                System.out.println(another_json_object.get("alerts"));

                PrintWriter out = new PrintWriter(new FileWriter("G:\\uniwerk\\programmeren\\java.json"));
                out.write(another_json_object.toString());
            }




            FileWriter xmlFile = new FileWriter("report.txt");
            xmlFile.write(new String(api.core.xmlreport(), StandardCharsets.UTF_8));







        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
