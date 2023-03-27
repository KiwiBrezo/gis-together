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
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import si.um.feri.gisgeodataservice.models.Geojson
import si.um.feri.gisgeodataservice.services.GeojsonService

@RestController
@RequestMapping("/api/v1/feature-collections")
class GeojsonController(@Autowired private var geojsonService: GeojsonService) {

    private var logger: Logger = LoggerFactory.getLogger(GeojsonController::class.java)

    @Operation(summary = "Get all geojson")
    @GetMapping("/")
    fun getAllGeojson() : ResponseEntity<Flux<Geojson>> {
        return try {
            logger.info("Connecting to Geojson Controller for getting all geojsons.")
            ResponseEntity(geojsonService.getAllGeojsons(), HttpStatusCode.valueOf(200))
        } catch (e: Exception) {
            logger.error("There was an error getting all geojsons: ${e.message}")
            e.printStackTrace()
            ResponseEntity(HttpStatusCode.valueOf(500))
        }
    }

    @Operation(summary = "Get geojson by Id")
    @GetMapping("/{id}")
    fun getByIdGeojson(@PathVariable id: String) : ResponseEntity<Mono<Geojson>> {
        return try {
            logger.info("Connecting to Geojson Controller for getting geojson with id (${id}).")
            ResponseEntity(geojsonService.getGeojsonById(id), HttpStatusCode.valueOf(200))
        } catch (e: Exception) {
            logger.error("There was an error getting geojson for id (${id}) : ${e.message}")
            e.printStackTrace()
            ResponseEntity(HttpStatusCode.valueOf(500))
        }
    }

    @Operation(summary = "Get geojson by name")
    @GetMapping("/name/{name}")
    fun getByNameGeojson(@PathVariable name: String) : ResponseEntity<Flux<Geojson>> {
        return try {
            logger.info("Connecting to Geojson Controller for getting geojson with name (${name}).")
            ResponseEntity(geojsonService.getGeojsonByGroup(name), HttpStatusCode.valueOf(200))
        } catch (e: Exception) {
            logger.error("There was an error getting geojson for name (${name}) : ${e.message}")
            e.printStackTrace()
            ResponseEntity(HttpStatusCode.valueOf(500))
        }
    }

}