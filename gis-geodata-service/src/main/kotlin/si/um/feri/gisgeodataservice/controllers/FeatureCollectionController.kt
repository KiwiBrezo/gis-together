package si.um.feri.gisgeodataservice.controllers

import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import si.um.feri.gisgeodataservice.models.FeatureCollection
import si.um.feri.gisgeodataservice.services.FeatureCollectionService

@RestController
@RequestMapping("/api/v1/feature-collections")
class FeatureCollectionController(var featureCollectionService: FeatureCollectionService) {

    @GetMapping("/")
    fun test() : ResponseEntity<Flux<FeatureCollection>> {
        return ResponseEntity(featureCollectionService.getAllFeatureCollections(), HttpStatusCode.valueOf(200))
    }

}