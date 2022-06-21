package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanAlert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "scan_report")
public class ScanReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String title;
    @NotBlank
    private String endpoint;
    @NotBlank
    private int grade;
    @Transient
    private List<ScanAlert> result;

    public ScanReport() {

    }

    public ScanReport(String title, String endpoint, int grade, List<ScanAlert> result) {
        this.title = title;
        this.endpoint = endpoint;
        this.grade = grade;
        this.result = result;
    }

    public ScanReport(long id, String title, String endpoint, int grade, List<ScanAlert> result) {
        this.id = id;
        this.title = title;
        this.endpoint = endpoint;
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

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public List<ScanAlert> getResult() {
        return result;
    }

    public void setResult(List<ScanAlert> result) {
        this.result = result;
    }
}
