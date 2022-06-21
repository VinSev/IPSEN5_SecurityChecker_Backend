//package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan;
//
//import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanCategory;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "result")
//public class ScanResult {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//    @Column(name = "categories")
//    @OneToMany(targetEntity = ScanCategory.class , fetch = FetchType.EAGER)
//    private List<ScanCategory> scanResults;
//
////   Constructors     ////////////////////////////////////////////////////////////////////////
//
//
//    public ScanResult(int headers, int XSSAndInjection, int certificates, int wordPressVulnerability, int version, int login, int dataSecurity, int SEO, List<ScanCategory> scanResults) {
//        this.scanResults = scanResults;
//    }
//
//    public ScanResult() {
//    }
//
//    //    Getters Setters
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//
//    public void setScanResults(List<ScanCategory> scanResults) {
//        this.scanResults = scanResults;
//    }
//}
