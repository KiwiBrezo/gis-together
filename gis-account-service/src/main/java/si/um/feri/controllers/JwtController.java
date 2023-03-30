package si.um.feri.controllers;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import si.um.feri.services.AccountService;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("/api/v1/jwt")
public class JwtController {

    @Inject
    private AccountService accountService;

    @GET
    @Path("/{token}")
    @Blocking
    public Boolean checkJwt(@PathParam("token") String token) {
        return accountService.checkJwtToken(token);
    }

}
