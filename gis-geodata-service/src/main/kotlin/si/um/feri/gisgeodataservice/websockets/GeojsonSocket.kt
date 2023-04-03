package si.um.feri.gisgeodataservice.websockets

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import si.um.feri.gisgeodataservice.grpc.AccountJwtService
import si.um.feri.gisgeodataservice.models.Geojson
import si.um.feri.gisgeodataservice.models.GeojsonsWSConnector
import si.um.feri.gisgeodataservice.services.GeojsonService


@Controller
class GeojsonSocket(@Autowired private var geojsonService: GeojsonService, @Autowired private var accountJwtService: AccountJwtService, private val messagingTemplate: SimpMessagingTemplate) {

    private var logger: Logger = LoggerFactory.getLogger(GeojsonSocket::class.java)

    @MessageMapping("/get-all")
    @SendTo("/topic/feature-collections")
    fun getAllGeojson(message: GeojsonsWSConnector): List<Geojson> {
        logger.info("Connected user with JWT token: ${message.jwtToken} to geojson socket.")

        if (!accountJwtService.checkJwtToken(message.jwtToken)) {
            return emptyList()
        }

        return geojsonService.getAllGeojsons().toStream().toList();
    }

}