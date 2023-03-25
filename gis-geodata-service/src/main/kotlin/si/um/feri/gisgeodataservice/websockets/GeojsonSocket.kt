package si.um.feri.gisgeodataservice.websockets

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import si.um.feri.gisgeodataservice.models.Geojson
import si.um.feri.gisgeodataservice.models.GeojsonsWSConnector
import si.um.feri.gisgeodataservice.services.GeojsonService


@Controller
class GeojsonSocket(@Autowired var geojsonService: GeojsonService) {

    @MessageMapping("/get-all")
    @SendTo("/topic/feature-collections")
    fun greeting(message: GeojsonsWSConnector): List<Geojson> {
        println(message.name)
        return geojsonService.getAllGeojsons().toStream().toList();
    }

}