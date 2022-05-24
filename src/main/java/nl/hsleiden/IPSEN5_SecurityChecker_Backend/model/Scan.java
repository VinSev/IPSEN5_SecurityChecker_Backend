package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.*;

@Entity
@Table(name = "scan")
public class Scan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scan_id")
    private long scanId;
    private String phoneNumber;
    private boolean declarationOwnership;
    @Column(name = "url")
    private String url;
    @OneToOne(mappedBy = "scan", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ScanResult scanResult;
    @Column(name = "update_date")
    private String updateDate;
//
////    Constructors


    public Scan(String phoneNumber, boolean declarationOwnership, String url, ScanResult scanResult, String updateDate) {
        this.phoneNumber = phoneNumber;
        this.declarationOwnership = declarationOwnership;
        this.url = url;
        this.scanResult = scanResult;
        this.updateDate = updateDate;
    }

    public Scan() {
    }

//    public Scan(String link, String name, String updateDate) {
//        this.link = link;
//        this.name = name;
//        this.updateDate = updateDate;

//    }

    //    Getters Setters


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isDeclarationOwnership() {
        return declarationOwnership;
    }

    public void setDeclarationOwnership(boolean declarationOwnership) {
        this.declarationOwnership = declarationOwnership;
    }

    public ScanResult getScanResult() {
        return scanResult;
    }

    public void setScanResult(ScanResult scanResult) {
        this.scanResult = scanResult;
    }

    public long getScanId() {
        return scanId;
    }

    public void setScanId(long scanId) {
        this.scanId = scanId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String link) {
        this.url = link;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
