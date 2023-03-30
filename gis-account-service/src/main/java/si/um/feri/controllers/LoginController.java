package si.um.feri.controllers;

import org.jboss.resteasy.reactive.RestResponse;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/api/v1/login")
public class LoginController {
    @POST
    public RestResponse<String> loginAccount() {
        return null;
    }
}
