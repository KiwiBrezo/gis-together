package si.um.feri.controllers;

import lombok.AllArgsConstructor;
import org.jboss.resteasy.reactive.RestResponse;
import si.um.feri.models.Account;
import si.um.feri.services.AccountService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/api/v1/register")
public class RegisterController {

    @Inject
    private AccountService accountService;

    @POST
    public Boolean registerAccount(@NotNull @Valid Account account) {
        return accountService.registerAccount(account);
    }

}
