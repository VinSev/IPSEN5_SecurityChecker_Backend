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
    private  ArrayList<String> listWithCategories = new ArrayList<>();


    public SeoScan() {
        keys.add("AIzaSyAxxka2F0BUg0njkvRKVzXzQ6vMDG8ny58");
    }

    @Override
    public void execute(ScanReport scanReport, String website) throws IOException, InterruptedException {
        this.listWithCategories.add("experimental_interaction_to_next_paint");
        this.listWithCategories.add("experimental_time_to_first_byte");
        this.listWithCategories.add("first_contentful_paint");
        this.listWithCategories.add("first_input_delay");
        this.listWithCategories.add("largest_contentful_paint");
        this.listWithCategories.add("cumulative_layout_shift");
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
        JSONObject result = new JSONObject(response.body());
        setGrade(getMedianOfSEO(result));
        setResult(result);
    }

    public int getMedianOfSEO(JSONObject result) {
        HashMap<String, Integer> medians = new HashMap<>();
        for (String category : this.listWithCategories) {
            Integer medianGrade = 0;
            if(category.equals("cumulative_layout_shift")){
                String medianStringGrade = (String) result.getJSONObject("record")
                        .getJSONObject("metrics").getJSONObject(category)
                        .getJSONObject("percentiles")
                        .get("p75");
               Float flmedianGrade = Float.parseFloat(medianStringGrade) * 10; // Om van 0.10 naar 10 te gaan
               medianGrade =  flmedianGrade.intValue();
            }else {
                medianGrade = (Integer) result.getJSONObject("record")
                        .getJSONObject("metrics").getJSONObject(category)
                        .getJSONObject("percentiles")
                        .get("p75");
                medianGrade = giveGradeToMedian(medianGrade, category);
            }
                medians.put(category, medianGrade);
        }
        int grade = 0;
        for (Integer categoryGrade : medians.values()) {
            grade += categoryGrade;
        }
        return grade / this.listWithCategories.size();
    }

    private Integer giveGradeToMedian(Integer medianGrade, String category) {
        switch (category) {
            case "experimental_interaction_to_next_paint":
            case "experimental_time_to_first_byte":
            case "first_contentful_paint":
                return gradeForCategory(medianGrade, 2500, 4000);
            case "cumulative_layout_shift":
                return gradeForCategory(medianGrade, 10, 25);
            case "first_input_delay":
                return gradeForCategory(medianGrade, 100, 300);
            default:
                return gradeForCategory(medianGrade, (int) 2.5, 4);
        }
    }

    private int gradeForCategory(Integer medianGrade, int fast, int slow) {
        int median = medianGrade.intValue();
        if (median < fast) {
            median =  median % fast / 100; // *10 om naar  een cijfer tot de 10 te gaan
        } else if (median > fast && median < slow) {
            median =  5;
        } else {
            median = median % slow / 5; // *5 om naar  een cijfer tot 5 te gaan
        }
        return median;
    }

    @Override
    public JSONObject getResult() throws IOException, InterruptedException {
        return super.getResult();
    }
}

