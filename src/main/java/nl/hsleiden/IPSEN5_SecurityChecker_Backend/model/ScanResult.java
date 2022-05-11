package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Dictionary;

public class ScanResult {
    @Id
    @ManyToOne(targetEntity = Scan.class , fetch = FetchType.EAGER)
    private String scan;
    @ManyToOne(targetEntity = Result.class , fetch = FetchType.EAGER)
    private String result;
    private int grade;
    private Dictionary<String,String> entry;

//    Constructor

    public ScanResult(String scan, String result, int grade, Dictionary<String, String> entry) {
        this.scan = scan;
        this.result = result;
        this.grade = grade;
        this.entry = entry;
    }

    public ScanResult(String result, int grade, Dictionary<String, String> entry) {
        this.result = result;
        this.grade = grade;
        this.entry = entry;
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

    public Dictionary<String, String> getEntry() {
        return entry;
    }

    public void setEntry(Dictionary<String, String> entry) {
        this.entry = entry;
    }
}
