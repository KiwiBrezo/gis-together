package si.um.feri.gisgeodataservice.controllers

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
class GeojsonController(@Autowired var geojsonService: GeojsonService) {

    @GetMapping("/")
    fun getAllGeojson() : ResponseEntity<Flux<Geojson>> {
        try {
            return ResponseEntity(geojsonService.getAllFeatureCollections(), HttpStatusCode.valueOf(200))
        } catch (e: Exception) {
            return ResponseEntity(HttpStatusCode.valueOf(500))
        }
    }

    @GetMapping("/{id}")
    fun getByIdGeojson(@PathVariable id: String) : ResponseEntity<Mono<Geojson>> {
        try {
            return ResponseEntity(geojsonService.getGeojsonById(id), HttpStatusCode.valueOf(200))
        } catch (e: Exception) {
            return ResponseEntity(HttpStatusCode.valueOf(500))
        }
    }

    @GetMapping("/name/{name}")
    fun getByNameGeojson(@PathVariable name: String) : ResponseEntity<Flux<Geojson>> {
        try {
            return ResponseEntity(geojsonService.getGeojsonByGroup(name), HttpStatusCode.valueOf(200))
        } catch (e: Exception) {
            return ResponseEntity(HttpStatusCode.valueOf(500))
        }
    }

}