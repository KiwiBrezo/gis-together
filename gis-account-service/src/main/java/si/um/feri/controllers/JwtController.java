package si.um.feri.controllers;

import io.smallrye.common.annotation.Blocking;
import org.jboss.resteasy.reactive.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.um.feri.services.AccountService;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/jwt")
public class JwtController {

    private Logger logger = LoggerFactory.getLogger(JwtController.class);

    @Inject
    private AccountService accountService;

    @GET
    @Path("/{token}")
    @Blocking
    public Object checkJwt(@NotEmpty @PathParam("token") String token) {
        logger.info(("Connecting to Jwt Controller for checking if jwt token is valid: ").concat(token));
        try {
            if (accountService.checkJwtToken(token)) {
                return RestResponse.ResponseBuilder.ok("JWT is valid", MediaType.TEXT_PLAIN_TYPE).build();
            }
        } catch (Exception e) {
            logger.error(("There was an error while checking if jwt token is valid: ").concat(e.getMessage()));
        }

        return RestResponse.ResponseBuilder.create(401, "JWT is not valid").build();
    }

}
