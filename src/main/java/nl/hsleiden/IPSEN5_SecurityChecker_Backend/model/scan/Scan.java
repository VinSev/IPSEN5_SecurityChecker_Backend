package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "scan")
public class Scan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scan_id")
    private long scanId;
    @Column(name = "url")
    private String url;
    @Column(name = "categories")
    @OneToMany(targetEntity = ScanCategory.class , fetch = FetchType.EAGER)
    private List<ScanCategory> categories;
    @Column(name = "update_date")
    private String updateDate;
    @ManyToOne(targetEntity = ScanUser.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private ScanUser user;

//   Constructors     ////////////////////////////////////////////////////////////////////////


    public Scan(String url, List<ScanCategory> categories, String updateDate, ScanUser user) {
        this.url = url;
        this.categories = categories;
        this.updateDate = updateDate;
        this.user = user;
    }

    public Scan() {
    }


    //    Getters Setters

    public String getUrl() {
        return url;
    }

    public ScanUser getUser() {
        return user;
    }

    public void setUser(ScanUser user) {
        this.user = user;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ScanCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ScanCategory> categories) {
        this.categories = categories;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
