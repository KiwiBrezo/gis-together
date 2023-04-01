package si.um.feri.grpc;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.um.feri.proto.JwtGrpc;
import si.um.feri.proto.JwtRequest;
import si.um.feri.proto.JwtValidReply;
import si.um.feri.services.AccountService;

import javax.inject.Inject;

@GrpcService
public class JwtGrpcService implements JwtGrpc {

    private Logger logger = LoggerFactory.getLogger(JwtGrpcService.class);

    @Inject
    private AccountService accountService;

    @Override
    public Uni<JwtValidReply> checkJwtValidity(JwtRequest request) {
        logger.info(("Connecting to gRPC service to check if jwt token is valid, token: ").concat(request.getToken()));

        String isValid;

        try {
            isValid = String.valueOf(accountService.checkJwtToken(request.getToken()));
        } catch (Exception e) {
            logger.error(("There was an error while checking if jwt token is valid: ").concat(e.getMessage()));
            isValid = "false";
        }

        String finalIsValid = isValid;
        logger.info(("The token is valid: ").concat(finalIsValid));
        return Uni.createFrom().item(() -> JwtValidReply.newBuilder().setValid(finalIsValid).build());
    }
}
