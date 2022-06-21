package nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.certificate;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.AbstractScan;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
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
        setResult(new JSONObject(response.body()));
        return new JSONObject(response.body()).getInt("scan_id");
    }

    @Override
    public JSONObject getResult() throws IOException, InterruptedException {
        String endpoint = "/results";
        String url = baseURL + endpoint + "?id=" + scanId;

        HttpClient http = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                        URI.create(url)
                )
                .GET()
                .build();

        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        setResult(new JSONObject(response.body()));
        return super.getResult();
    }

    private boolean isFinished() throws IOException, InterruptedException {
        return getResult().get("completion_perc").equals(100);
    }

}

