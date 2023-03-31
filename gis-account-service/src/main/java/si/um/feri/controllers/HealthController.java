package si.um.feri.controllers;

import io.smallrye.common.annotation.Blocking;
import org.jboss.resteasy.reactive.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/api/v1")
public class HealthController {

    private Logger logger = LoggerFactory.getLogger(HealthController.class);

    @GET
    @Path("/ping")
    @Blocking
    public Object pong() {
        logger.info("Connecting to Health Controller for Pong.");
        return RestResponse.ResponseBuilder.ok( "Pong", MediaType.TEXT_PLAIN_TYPE).build();
    }
}
