package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "scan_user")
public class ScanUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    @NotBlank
    private String website;
    @NotBlank
    private boolean ownership;

    public ScanUser() {

    }

    public ScanUser(String name, String email, String website, boolean ownership) {
        this.name = name;
        this.email = email;
        this.website = website;
        this.ownership = ownership;
    }

    public ScanUser(long id, String name, String email, String website, boolean ownership) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.website = website;
        this.ownership = ownership;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean getOwnership() {
        return ownership;
    }

    public void setOwnership(boolean ownership) {
        this.ownership = ownership;
    }


}
