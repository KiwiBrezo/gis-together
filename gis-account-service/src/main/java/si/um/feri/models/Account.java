package si.um.feri.models;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Account {
    private Integer accountId;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
