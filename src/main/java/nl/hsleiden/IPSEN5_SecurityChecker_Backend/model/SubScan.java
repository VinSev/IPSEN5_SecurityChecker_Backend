package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.*;

@Entity
@Table(name = "sub_scan")
public class SubScan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String name;
    private String url;


//   Constructors     ////////////////////////////////////////////////////////////////////////

    public SubScan(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public SubScan(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public SubScan() {
    }

//    Getters and Setters  ////////////////////////////////////////////////////////////////////////

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
