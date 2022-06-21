package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan;

import javax.persistence.*;
import java.lang.String;

@Entity
@Table(name = "scan_category")
public class ScanCategory {
    @Id
    private long id;
    @MapsId
    @ManyToOne(targetEntity = Scan.class , fetch = FetchType.EAGER)
    private Scan scan;
    private boolean loading;
    private int grade;
    @Transient
    private String result;
    @MapsId
    @ManyToOne(targetEntity = ApiScan.class , fetch = FetchType.EAGER)
    private ApiScan apiScan;

//   Constructors     ////////////////////////////////////////////////////////////////////////

    public ScanCategory() {
    }

    public ScanCategory(Scan scan, boolean loading, int grade, String result, ApiScan apiScan) {
        this.scan = scan;
        this.loading = loading;
        this.grade = grade;
        this.result = result;
        this.apiScan = apiScan;
    }

    public ScanCategory(Scan scan, int i, ApiScan apiScan) {
    }

    //    Getters Setters


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

    public ApiScan getApiScan() {
        return apiScan;
    }

    public void setApiScan(ApiScan apiScan) {
        this.apiScan = apiScan;
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

    public ApiScan getSubScan() {
        return apiScan;
    }

    public void setSubScan(ApiScan sub_scan) {
        this.apiScan = sub_scan;
    }
}
