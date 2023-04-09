package si.um.feri.gisgeodataservice.services

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["new-geojson-published"])
class NewGeojsonReciverService(@Autowired private var geojsonPushData: GeojsonPushData) {

    private var logger: Logger = LoggerFactory.getLogger(NewGeojsonReciverService::class.java)

    @RabbitHandler
    fun receiveNewGeojsonId(insertedId: String) {
        logger.info("Got message to publish new geojson with id: $insertedId")
        geojsonPushData.sendNewGeojsonById(insertedId);
    }

}