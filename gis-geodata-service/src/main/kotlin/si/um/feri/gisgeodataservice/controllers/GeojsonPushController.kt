package si.um.feri.gisgeodataservice.controllers

import io.swagger.v3.oas.annotations.Operation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import si.um.feri.gisgeodataservice.services.GeojsonPushData

@RestController
@RequestMapping("/api/v1/push/feature-collections")
class GeojsonPushController(@Autowired private var geojsonPushData: GeojsonPushData) {

    private var logger: Logger = LoggerFactory.getLogger(GeojsonPushController::class.java)

    @Operation(summary = "Push all geojsons with socket")
    @GetMapping("/")
    fun pushAllGeojsons(): ResponseEntity<String> {
        return try {
            logger.info("Connecting to Geojson Push Controller for pushing all geojson with sockets.")
            geojsonPushData.sendAllGeojson();
            ResponseEntity("done", HttpStatusCode.valueOf(200))
        } catch (e: Exception) {
            logger.error("There was an error pushing data with all geojsons: ${e.message}")
            e.printStackTrace()
            ResponseEntity(e.message, HttpStatusCode.valueOf(500))
        }
    }

    @Operation(summary = "Push geojsons by Id with socket")
    @GetMapping("/{id}")
    fun pushFeatureCollectionWithId(@PathVariable id: String): ResponseEntity<String> {
        return try {
            logger.info("Connecting to Geojson Push Controller for pushing geojson with id (${id}) with sockets.")
            geojsonPushData.sendNewGeojsonById(id)
            ResponseEntity("done", HttpStatusCode.valueOf(200))
        } catch (e: Exception) {
            logger.error("There was an error pushing data for geojson with id (${id}) : ${e.message}")
            e.printStackTrace()
            ResponseEntity(e.message, HttpStatusCode.valueOf(500))
        }
    }

}