package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @OneToOne(targetEntity = ScanUser.class, fetch = FetchType.EAGER)
    private ScanUser scanUser;
    @NotBlank
    @OneToMany(targetEntity = ScanReport.class, fetch = FetchType.EAGER)
    private List<ScanReport> scanReports;
    @NotBlank
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date dateCreated;

    public Report() {
    }

    public Report(long id, ScanUser scanUser, List<ScanReport> scanReports, Date dateCreated) {
        this.id = id;
        this.scanUser = scanUser;
        this.scanReports = scanReports;
        this.dateCreated = dateCreated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ScanUser getScanUser() {
        return scanUser;
    }

    public void setScanUser(ScanUser scanUser) {
        this.scanUser = scanUser;
    }

    public List<ScanReport> getScanReports() {
        return scanReports;
    }

    public void setScanReports(List<ScanReport> scanReports) {
        this.scanReports = scanReports;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
