package si.um.feri.gisgeodataservice.websockets

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import si.um.feri.gisgeodataservice.controllers.HealthController
import si.um.feri.gisgeodataservice.models.Geojson
import si.um.feri.gisgeodataservice.models.GeojsonsWSConnector
import si.um.feri.gisgeodataservice.services.GeojsonService


@Controller
class GeojsonSocket(@Autowired private var geojsonService: GeojsonService) {

    private var logger: Logger = LoggerFactory.getLogger(GeojsonSocket::class.java)

    @MessageMapping("/get-all")
    @SendTo("/topic/feature-collections")
    fun greeting(message: GeojsonsWSConnector): List<Geojson> {
        logger.info("Connected user with name: ${message.name}, to geojson socket.")
        return geojsonService.getAllGeojsons().toStream().toList();
    }

}