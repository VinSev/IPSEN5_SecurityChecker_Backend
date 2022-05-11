package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Dictionary;

public class ScanResult {
    @Id
    @ManyToOne(targetEntity = Scan.class , fetch = FetchType.EAGER)
    private String scan;
    @ManyToOne(targetEntity = Result.class , fetch = FetchType.EAGER)
    private String result;
    private int grade;
    private Dictionary<String,String> component;

//    Constructor

    public ScanResult(String scan, String result, int grade, Dictionary<String, String> component) {
        this.scan = scan;
        this.result = result;
        this.grade = grade;
        this.component = ScanResult.this.component;
    }

    public ScanResult(String result, int grade, Dictionary<String, String> component) {
        this.result = result;
        this.grade = grade;
        this.component = ScanResult.this.component;
    }

    public ScanResult() {
    }

    //    Getters Setters
    public String getScan() {
        return scan;
    }

    public void setScan(String scan) {
        this.scan = scan;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Dictionary<String, String> setComponent() {
        return component;
    }

    public void setComponent(Dictionary<String, String> component) {
        this.component = ScanResult.this.component;
    }
}
