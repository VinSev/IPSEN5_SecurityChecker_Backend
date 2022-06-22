package nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.seo;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanAlert;
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
import java.util.concurrent.TimeUnit;

public class SeoScan extends AbstractScan {
    private final String baseURL = "https://chromeuxreport.googleapis.com/v1/records:queryRecord";
    private final List<String> keys = new ArrayList<>();
    private ArrayList<String> listWithCategories = new ArrayList<>();


    public SeoScan() {
        keys.add("AIzaSyAxxka2F0BUg0njkvRKVzXzQ6vMDG8ny58");
    }

    @Override
    public void execute(ScanReport scanReport, String website) throws IOException, InterruptedException {
        super.execute(scanReport, website);

        this.listWithCategories.add("experimental_interaction_to_next_paint");
        this.listWithCategories.add("experimental_time_to_first_byte");
        this.listWithCategories.add("first_contentful_paint");
        this.listWithCategories.add("first_input_delay");
        this.listWithCategories.add("largest_contentful_paint");
        this.listWithCategories.add("cumulative_layout_shift");

        startScan(keys.get(0));
        do {
            TimeUnit.SECONDS.sleep(1);
        } while (!isFinished());
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
        JSONObject result = new JSONObject(response.body());

        super.setTemporaryResult(result);
    }

    private int calculateGradeByCategory(JSONObject metric) {
        double good = metric.getJSONArray("histogram").getJSONObject(0).getDouble("end");
        double poor = metric.getJSONArray("histogram").getJSONObject(2).getDouble("start");
        double average = metric.getJSONObject("percentiles").getDouble("p75");
        return calculateGrade(average, good, poor);
    }

    private int calculateGrade(double average, double good, double poor) {
        if (average <= good) {
            return 10;
        } else if (average > poor) {
            return 1;
        } else {
            return 4;
        }
    }

    @Override
    public List<ScanAlert> getResult() throws IOException, InterruptedException {
        int totalPoints = 0;

        JSONObject metrics = super.getTemporaryResult().getJSONObject("record").getJSONObject("metrics");
        for (String name : metrics.keySet()) {
            JSONObject metric = metrics.getJSONObject(name);

            int point = calculateGradeByCategory(metric);
            totalPoints += point;

            ScanAlert scanAlert = new ScanAlert(
                    name,
                    point > 5,
                    metric.getJSONObject("percentiles").getDouble("p75") + " ms"
            );
            super.getResult().add(scanAlert);
        }
        super.setGrade(totalPoints / metrics.length());

        return super.getResult();
    }

    private boolean isFinished() {
        return super.getTemporaryResult() != null;
    }
}

