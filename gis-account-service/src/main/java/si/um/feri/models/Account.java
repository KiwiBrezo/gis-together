package si.um.feri.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "Acount")
@Table(name = "acount")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "email")
    @NotEmpty
    private String email;

    @Column(name = "username")
    @NotEmpty
    private String username;

    @Column(name = "password")
    private String password;
}
