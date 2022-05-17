package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.HashMap;

public class ScanResult_TEST {
    @Id
    @ManyToOne(targetEntity = ScanResult.class , fetch = FetchType.EAGER)
    private ScanResult scanResult;
    @ManyToOne(targetEntity = Scan.class , fetch = FetchType.EAGER)
    private Scan scan;
    private int grade;
    private HashMap<String,String> scanComponent;

//    Constructor

    public ScanResult_TEST(Scan scan, ScanResult scanResult, int grade, HashMap<String, String> scanComponent) {
        this.scan = scan;
        this.scanResult = scanResult;
        this.grade = grade;
        this.scanComponent = scanComponent;
    }

    public ScanResult_TEST(ScanResult scanResult, int grade, HashMap<String, String> scanComponent) {
        this.scanResult = scanResult;
        this.grade = grade;
        this.scanComponent = scanComponent;
    }

    public ScanResult_TEST() {
    }

    //    Getters Setters
    public Scan getScan() {
        return scan;
    }

    public void setScan(Scan scan) {
        this.scan = scan;
    }

    public ScanResult getResult() {
        return scanResult;
    }

    public void setResult(ScanResult scanResult) {
        this.scanResult = scanResult;
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
