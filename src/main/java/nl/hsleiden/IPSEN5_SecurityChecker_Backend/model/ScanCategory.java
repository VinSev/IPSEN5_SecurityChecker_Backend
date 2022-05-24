package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.*;

@Entity
@Table(name = "scan_category")
public class ScanCategory {
    @Id
    private String id;

    @MapsId
    @ManyToOne(targetEntity = Scan.class , fetch = FetchType.EAGER)
    private Scan scan;
    private int grade;
    @MapsId
    @ManyToOne(targetEntity = SubScan.class , fetch = FetchType.EAGER)
    private SubScan subScan;

//    Constructor   ////////////////////////////////////////////////////////////////////////


    public ScanCategory(Scan scan, int grade, SubScan subScan) {
        this.scan = scan;
        this.grade = grade;
        this.subScan = subScan;
    }

    public ScanCategory() {
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
