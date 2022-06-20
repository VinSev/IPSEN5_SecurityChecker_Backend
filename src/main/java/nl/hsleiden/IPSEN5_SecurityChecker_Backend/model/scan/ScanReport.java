package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan;

import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "scan_report")
public class ScanReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String title;
    @NotBlank
    private int grade;
    @Transient
    private JSONObject result;

    public ScanReport() {

    }

    public ScanReport(long id, String title, int grade, JSONObject result) {
        this.id = id;
        this.title = title;
        this.grade = grade;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }
}
