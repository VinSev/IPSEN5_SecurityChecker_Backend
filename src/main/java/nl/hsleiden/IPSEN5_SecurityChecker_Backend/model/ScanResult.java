package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.HashMap;

public class ScanResult {
    @Id
    @ManyToOne(targetEntity = Result.class , fetch = FetchType.EAGER)
    private Result result;
    @ManyToOne(targetEntity = Scan.class , fetch = FetchType.EAGER)
    private Scan scan;
    private int grade;
    private HashMap<String,String> scanComponent;

//    Constructor

    public ScanResult(Scan scan, Result result, int grade, HashMap<String, String> scanComponent) {
        this.scan = scan;
        this.result = result;
        this.grade = grade;
        this.scanComponent = scanComponent;
    }

    public ScanResult(Result result, int grade, HashMap<String, String> scanComponent) {
        this.result = result;
        this.grade = grade;
        this.scanComponent = scanComponent;
    }

    public ScanResult() {
    }

    //    Getters Setters
    public Scan getScan() {
        return scan;
    }

    public void setScan(Scan scan) {
        this.scan = scan;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public HashMap<String, String> setComponent() {
        return scanComponent;
    }

    public void setScanComponent(HashMap<String, String> scanComponent) {
        this.scanComponent = scanComponent;
    }
}
