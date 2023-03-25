package si.um.feri.gisgeodataservice.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import si.um.feri.gisgeodataservice.models.Geojson
import si.um.feri.gisgeodataservice.repository.GeojsonRepository

@Service
class GeojsonService(@Autowired var geojsonRepository: GeojsonRepository) {
    fun getAllFeatureCollections(): Flux<Geojson> {
        return geojsonRepository.findAll()
    }

    fun getGeojsonById(id: String): Mono<Geojson> {
        return geojsonRepository.findById(id)
    }

    fun getGeojsonByGroup(name: String): Flux<Geojson> {
        return geojsonRepository.getGeojsonByName(name);
    }
}