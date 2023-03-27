package si.um.feri.gisgeodataservice.services

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import si.um.feri.gisgeodataservice.controllers.HealthController

@Service
class GeojsonPushData(@Autowired private var geojsonService: GeojsonService,
                      @Autowired private var simpleMessagingTemplate: SimpMessagingTemplate) {

    private var logger: Logger = LoggerFactory.getLogger(GeojsonPushData::class.java)

    fun sendAllGeojson() {
        logger.info("Service connecting to geojson repository to push all geojsons to socket.")
        simpleMessagingTemplate.convertAndSend("/topic/push/feature-collections/all", geojsonService.getAllGeojsons().buffer().blockLast()!!)
    }

    fun sendNewGeojsonById(id: String) {
        logger.info("Service connecting to geojson repository to push geojsons with id (${id}) to socket.")
        simpleMessagingTemplate.convertAndSend("/topic/push/feature-collections/new", geojsonService.getGeojsonById(id).block()!!)
    }

}