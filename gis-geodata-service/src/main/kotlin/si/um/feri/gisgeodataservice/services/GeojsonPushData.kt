package si.um.feri.gisgeodataservice.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class GeojsonPushData(@Autowired private var geojsonService: GeojsonService,
                      @Autowired private var simpleMessagingTemplate: SimpMessagingTemplate) {

    fun sendNewGeojsonById(id: String) {
        simpleMessagingTemplate.convertAndSend("/topic/push/feature-collections", geojsonService.getGeojsonById(id).block()!!)
    }

}