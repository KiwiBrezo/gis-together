package si.um.feri.controllers;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestResponse;
import si.um.feri.models.Account;
import si.um.feri.services.AccountService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/login")
public class LoginController {

    @Inject
    private AccountService accountService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Blocking
    public Uni<String> loginAccount(@NotNull @Valid Account account) {
        return Uni.createFrom().item(accountService.loginAccount(account));
    }

}
