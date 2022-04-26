package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import java.util.List;

public class UserResponse {
    private String email;
    private List<String> roles;

    public UserResponse(String email, List<String> roles) {
        this.email = email;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
