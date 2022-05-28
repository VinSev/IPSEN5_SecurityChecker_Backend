package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.*;

@Entity
@Table(name = "result")
public class ScanResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    @MapsId
    private Scan scan;
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


//    @Column(name = "scans_results")
//    @OneToMany(targetEntity = ScanResult.class , fetch = FetchType.EAGER)
//    private List<ScanResult> scanResults;

    public Scan getScan() {
        return scan;
    }

//    public Result(long resultId, String date, String url, List<ScanResult> scanResults, boolean isFinished) {
//        this.resultId = resultId;
//        this.date = date;
//        this.url = url;
//        this.scanResults = scanResults;
//        this.isFinished = isFinished;
//    }


    public ScanResult(Scan scan, int headers, int XSSAndInjection, int certificates, int wordPressVulnerability, int version, int login, int dataSecurity, int SEO) {
        this.scan = scan;
        this.headers = headers;
        this.XSSAndInjection = XSSAndInjection;
        this.certificates = certificates;
        this.wordPressVulnerability = wordPressVulnerability;
        this.version = version;
        this.login = login;
        this.dataSecurity = dataSecurity;
        this.SEO = SEO;
    }

    public ScanResult() {
    }


    //    Setters And Getters


    public void setScan(Scan scan) {
        this.scan = scan;
    }

    public int getHeaders() {
        return headers;
    }

    public void setHeaders(int headers) {
        this.headers = headers;
    }

    public int getXSSAndInjection() {
        return XSSAndInjection;
    }

    public void setXSSAndInjection(int XSSAndInjection) {
        this.XSSAndInjection = XSSAndInjection;
    }

    public int getCertificates() {
        return certificates;
    }

    public void setCertificates(int certificates) {
        this.certificates = certificates;
    }

    public int getWordPressVulnerability() {
        return wordPressVulnerability;
    }

    public void setWordPressVulnerability(int wordPressVulnerability) {
        this.wordPressVulnerability = wordPressVulnerability;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getDataSecurity() {
        return dataSecurity;
    }

    public void setDataSecurity(int dataSecurity) {
        this.dataSecurity = dataSecurity;
    }

    public int getSEO() {
        return SEO;
    }

    public void setSEO(int SEO) {
        this.SEO = SEO;
    }
}
