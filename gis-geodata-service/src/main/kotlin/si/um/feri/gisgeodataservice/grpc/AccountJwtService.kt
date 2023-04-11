package si.um.feri.gisgeodataservice.grpc

import io.grpc.Channel
import io.grpc.ManagedChannelBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import si.um.feri.gisgeodataservice.configs.AppProperties
import si.um.feri.gisgeodataservice.proto.JwtGrpcGrpc
import si.um.feri.gisgeodataservice.proto.JwtRequest

@Service
class AccountJwtService(@Autowired private var appProperties: AppProperties) {

    private val grpcChanel: Channel = ManagedChannelBuilder.forAddress(appProperties.getAccountServiceUrl(), 9000)
        .usePlaintext()
        .build()

    private val logger: Logger = LoggerFactory.getLogger(AccountJwtService::class.java)

    fun checkJwtToken(token: String): Boolean {
        logger.info("Checking if JWT token is valid and not expired: $token")

        return try {
            val stub = JwtGrpcGrpc.newBlockingStub(grpcChanel)

            val response = stub.checkJwtValidity(JwtRequest.newBuilder().setToken(token).build())

            logger.info("JWT token: $token is valid: ${response.valid.toString().toBoolean()}")

            return response.valid.toString().toBoolean()
        } catch (e: Exception) {
            logger.error("There was an error while checking if JWT is valid: ${e.message}")
            e.printStackTrace()
            return false
        }

    }

}