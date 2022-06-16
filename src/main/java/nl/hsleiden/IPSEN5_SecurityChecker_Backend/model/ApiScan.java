package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.*;

@Entity
@Table(name = "api_scan")
public class ApiScan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;// header
    private String url; // header


//   Constructors     ////////////////////////////////////////////////////////////////////////

    public ApiScan(long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public ApiScan(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public ApiScan() {
    }

    //    Getters Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
