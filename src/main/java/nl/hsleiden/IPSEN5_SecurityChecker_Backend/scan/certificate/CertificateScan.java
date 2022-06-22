package nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.certificate;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanAlert;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.AbstractScan;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
@Service
public class CertificateScan extends AbstractScan  {
    private final String baseURL = "https://tls-observatory.services.mozilla.com/api/v1";
    private int scanId;

    @Override
    public void execute(ScanReport scanReport, String website) throws IOException, InterruptedException {
        super.execute(scanReport, website);

        startScan();
        do {
            scanId = getScanId();
            TimeUnit.SECONDS.sleep(1);
        } while (!isFinished());

        int grade = ((JSONObject) super.getTemporaryResult().getJSONArray("analysis").get(4)).getJSONObject("result").getInt("grade") / 10;// Er word een cijfer  tussne de 1 en 100 gegeven. Delen door 10 helpt om een cijfer tussen de 1 en de 10 te krijgen
        super.setGrade(grade);
    }

    private void startScan() throws IOException, InterruptedException {
        String endpoint = "/scan";
        String url = baseURL + endpoint + "?target=" + getWebsite();

        Map<String, String> body = new HashMap<>();
        body.put("rescan", "true");
        body.put("hidden", "true");

        String requestBody = new ObjectMapper()
                .writeValueAsString(body);

        HttpClient http = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                        URI.create(url)
                )
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        http.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public int getScanId() throws IOException, InterruptedException {
        String endpoint = "/scan";
        String url = baseURL + endpoint + "?target=" + getWebsite();

        Map<String, String> body = new HashMap<>();
        body.put("rescan", "true");
        body.put("hidden", "true");

        String requestBody = new ObjectMapper()
                .writeValueAsString(body);

        HttpClient http = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                        URI.create(url)
                )
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        setTemporaryResult(new JSONObject(response.body()));

        return super.getTemporaryResult().getInt("scan_id");
    }

    @Override
    public List<ScanAlert> getResult() throws IOException, InterruptedException {
        String endpoint = "/results";
        String url = baseURL + endpoint + "?id=" + scanId;

        HttpClient http = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                        URI.create(url)
                )
                .GET()
                .build();

        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        super.setTemporaryResult(new JSONObject(response.body()));

       try {
           JSONObject result = super.getTemporaryResult().getJSONArray("analysis").getJSONObject(3).getJSONObject("result").getJSONObject("failures");
           JSONArray modern = result.getJSONArray("modern");
           JSONArray old = result.getJSONArray("old");
           JSONArray intermediate = result.getJSONArray("intermediate");

           super.getResult().clear();

           for(int i = 0; i < modern.length() - 1; i++) {
               ScanAlert scanAlert = new ScanAlert(
                       "Modern Protocol",
                       !modern.getString(i).startsWith("remove"),
                       modern.getString(i)
               );
               if(scanAlert.getDescription().split(" ").length <= 10) {
                   super.getResult().add(scanAlert);
               }
           }

           for(int i = 0; i < old.length() - 1; i++) {
               ScanAlert scanAlert = new ScanAlert(
                       "Old Protocol",
                       !old.getString(i).startsWith("remove"),
                       old.getString(i)
               );
               if(scanAlert.getDescription().split(" ").length <= 10) {
                   super.getResult().add(scanAlert);
               }
           }

           for(int i = 0; i < intermediate.length() - 1; i++) {
               ScanAlert scanAlert = new ScanAlert(
                       "Intermediate Protocol",
                       !intermediate.getString(i).startsWith("remove"),
                       intermediate.getString(i)
               );
               if(scanAlert.getDescription().split(" ").length <= 10) {
                   super.getResult().add(scanAlert);
               }
           }
       } catch (JSONException ignore) {}

        return super.getResult();
    }

    private boolean isFinished() throws IOException, InterruptedException {
        getResult();
        return super.getTemporaryResult() != null && super.getTemporaryResult().get("completion_perc").equals(100);
    }

}

