package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.*;

@Entity
@Table(name = "scan_category")
public class ScanCategory {
    @Id
    private long id;

    @MapsId
    @ManyToOne(targetEntity = Scan.class , fetch = FetchType.EAGER)
    private Scan scan;
    private boolean loading;
    private String result;
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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Scan getScan() {
        return scan;
    }

    public void setScan(Scan scan) {
        this.scan = scan;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public SubScan getSubScan() {
        return subScan;
    }

    public void setSubScan(SubScan sub_scan) {
        this.subScan = sub_scan;
    }
}
