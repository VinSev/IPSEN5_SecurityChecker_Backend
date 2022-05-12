package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "scans")
public class Scan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    private String email;
    @NotBlank
    private String url;

    public Scan(String name, String email, String url) {
        this.name = name;
        this.email = email;
        this.url = url;
    }

    public Scan(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Scan() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
