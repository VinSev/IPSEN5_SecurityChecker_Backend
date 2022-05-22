package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.*;

@Entity
@Table(name = "scan_result")
public class ScanResult_TEST {
    @Id
    private String id;

    @MapsId
    @ManyToOne(targetEntity = Scan.class , fetch = FetchType.EAGER)
    private Scan scan;
    private int grade;
    @ManyToOne(targetEntity = Scan.class , fetch = FetchType.EAGER)
    private SubScan subScan;

//    Constructor   ////////////////////////////////////////////////////////////////////////


    public ScanResult_TEST(Scan scan, int grade, String url, String name) {
        this.scan = scan;
        this.grade = grade;
    }

    public ScanResult_TEST(int grade, String url, String name) {
        this.grade = grade;
    }

    public ScanResult_TEST() {
    }

    //    Getters Setters   ////////////////////////////////////////////////////////////////////////


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Scan getScan() {
        return scan;
    }

    public void setScan(Scan scan) {
        this.scan = scan;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public SubScan getSubScan() {
        return subScan;
    }

    public void setSubScan(SubScan sub_scan) {
        this.subScan = sub_scan;
    }
}
