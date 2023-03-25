package si.um.feri.gisgeodataservice.repository

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import si.um.feri.gisgeodataservice.models.Geojson

@Repository
interface GeojsonRepository : ReactiveMongoRepository<Geojson, String> {
    fun getGeojsonByName(name: String): Flux<Geojson>

}