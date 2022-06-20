package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "result")
public class ScanResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int headers;
    private int XSSAndInjection;
    private int certificates;
    private int wordPressVulnerability;
    @Column(name = "version_url")
    private int version;
    @Column(name = "login_url")
    private int login;
    private int dataSecurity;
    private int SEO;
    @Column(name = "categories")
    @OneToMany(targetEntity = ScanCategory.class , fetch = FetchType.EAGER)
    private List<ScanCategory> scanResults;

//   Constructors     ////////////////////////////////////////////////////////////////////////


    public ScanResult(int headers, int XSSAndInjection, int certificates, int wordPressVulnerability, int version, int login, int dataSecurity, int SEO, List<ScanCategory> scanResults) {
        this.headers = headers;
        this.XSSAndInjection = XSSAndInjection;
        this.certificates = certificates;
        this.wordPressVulnerability = wordPressVulnerability;
        this.version = version;
        this.login = login;
        this.dataSecurity = dataSecurity;
        this.SEO = SEO;
        this.scanResults = scanResults;
    }

    public ScanResult() {
    }

    //    Getters Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public void setScanResults(List<ScanCategory> scanResults) {
        this.scanResults = scanResults;
    }
}
