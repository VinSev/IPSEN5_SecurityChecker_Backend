package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @OneToOne(targetEntity = ScanUser.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private ScanUser scanUser;
    @OneToMany(targetEntity = ScanReport.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<ScanReport> scanReports = new ArrayList<>();
    private String dateCreated;

    @PrePersist
    protected void prePersist() {
        if (Objects.equals(this.dateCreated, "")) dateCreated = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public Report() {
    }

    public Report(List<ScanReport> scanReports, ScanUser scanUser) {
        this.scanUser = scanUser;
        this.scanReports = scanReports;
    }

    public Report(long id, List<ScanReport> scanReports, ScanUser scanUser, String dateCreated) {
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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
