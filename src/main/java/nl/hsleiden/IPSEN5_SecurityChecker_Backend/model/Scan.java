package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.*;

@Entity
@Table(name = "scan")
public class Scan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scan_id")
    private long scanId;
    private String link;
    private String name;
    @Column(name = "update_date")
    private String updateDate;

//    Constructors
    public Scan(long scanId, String link, String name, String updateDate) {
        this.scanId = scanId;
        this.link = link;
        this.name = name;
        this.updateDate = updateDate;
    }

    public Scan(String link, String name, String updateDate) {
        this.link = link;
        this.name = name;
        this.updateDate = updateDate;
    }

    public Scan() {
    }

    //    Getters Setters

    public long getScanId() {
        return scanId;
    }

    public void setScanId(long scanId) {
        this.scanId = scanId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
