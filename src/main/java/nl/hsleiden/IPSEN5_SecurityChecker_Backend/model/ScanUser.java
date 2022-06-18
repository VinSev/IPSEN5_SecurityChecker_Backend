package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "scan_user")
public class ScanUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Email
    private String email;
    @NotBlank
    private String name;
    private String telephone;
    @NotBlank
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ScanResult> scans;

    public ScanUser(int id, String email, String name, String telephone, Set<ScanResult> scans) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.telephone = telephone;
        this.scans = scans;
    }

    public ScanUser(String email, String name, String telephone, Set<ScanResult> scans) {
        this.email = email;
        this.name = name;
        this.telephone = telephone;
        this.scans = scans;
    }

    public ScanUser() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ScanResult> getScans() {
        return scans;
    }

    public void setScans(Set<ScanResult> scans) {
        this.scans = scans;
    }
}

