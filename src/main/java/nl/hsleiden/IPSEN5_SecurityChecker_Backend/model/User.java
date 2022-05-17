package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Email
    private String email;
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    private Set<Role> role;
    @NotBlank
    private String name;
    private String telephone;
    @NotBlank
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<ScanResult> scans;
    public User() {

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<ScanResult> getScans() {
        return scans;
    }

    public void setScans(Set<ScanResult> scans) {
        this.scans = scans;
    }
}
