package nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.seo;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.AbstractScan;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeoScan extends AbstractScan {
    private final String baseURL = "https://chromeuxreport.googleapis.com/v1/records:queryRecord";
    private final List<String> keys = new ArrayList<>();

    public SeoScan() {
        keys.add("AIzaSyAxxka2F0BUg0njkvRKVzXzQ6vMDG8ny58");
    }

    @Override
    public void execute(ScanReport scanReport, String website) throws IOException, InterruptedException {
        super.execute(scanReport, website);

        startScan(keys.get(0));
    }

    private void startScan(String key) throws IOException, InterruptedException {
        String url = baseURL + "?key=" + key;

        Map<String, String> body = new HashMap<>();
        body.put("origin", "https://" + getWebsite());

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
    }

    @Override
    public JSONObject getResult() throws IOException, InterruptedException {
        return super.getResult();
    }
}

