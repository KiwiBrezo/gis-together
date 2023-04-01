package si.um.feri.controllers;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.um.feri.models.Account;
import si.um.feri.services.AccountService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Inject
    private AccountService accountService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Blocking
    public Object loginAccount(@NotNull @Valid Account account) {
        logger.info("Connecting to Login Controller for login in the user.");
        try {
            String jwt = accountService.loginAccount(account);
            if (jwt == null) {
                return RestResponse.ResponseBuilder.create(401, "User data not valid").build();
            }
            return RestResponse.ResponseBuilder.ok(jwt, MediaType.TEXT_PLAIN).build();
        } catch (Exception e) {
            logger.error(("There was an error while login the user in: ").concat(e.getMessage()));
            e.printStackTrace();
            return RestResponse.ResponseBuilder.create(500, e.getMessage()).build();
        }
    }

}
