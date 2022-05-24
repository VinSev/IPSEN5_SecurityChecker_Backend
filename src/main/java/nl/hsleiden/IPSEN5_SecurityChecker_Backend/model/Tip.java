package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tips")
public class Tip {
    @Id
    private Integer id;
    @NotBlank
    private String text;
}
