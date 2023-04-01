package si.um.feri.controllers;

import lombok.AllArgsConstructor;
import org.jboss.resteasy.reactive.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.um.feri.models.Account;
import si.um.feri.services.AccountService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/v1/register")
public class RegisterController {

    private Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Inject
    private AccountService accountService;

    @POST
    public Object registerAccount(@NotNull @Valid Account account) {
        logger.info("Connecting to Register Controller for register new user.");
        try {
            if (accountService.registerAccount(account)) {
                return RestResponse.ResponseBuilder.ok("Account created", MediaType.TEXT_PLAIN_TYPE).build();
            }

            return RestResponse.ResponseBuilder.create(400, "Account cant be created").build();
        } catch (Exception e) {
            logger.error(("There was an error creating new user: ").concat(e.getMessage()));
            e.printStackTrace();
            return RestResponse.ResponseBuilder.create(500, e.getMessage()).build();
        }
    }

}
