package si.um.feri.gisgeodataservice.services

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import si.um.feri.gisgeodataservice.models.Geojson
import si.um.feri.gisgeodataservice.repository.GeojsonRepository

@Service
class GeojsonService(@Autowired private var geojsonRepository: GeojsonRepository) {

    private var logger: Logger = LoggerFactory.getLogger(GeojsonService::class.java)

    fun getAllGeojsons(): Flux<Geojson> {
        logger.info("Service connecting to geojson repository to get all geojsons.")
        return geojsonRepository.findAll()
    }

    fun getGeojsonById(id: String): Mono<Geojson> {
        logger.info("Service connecting to geojson repository to get geojsons with id: ${id}.")
        return geojsonRepository.findById(id)
    }

    fun getGeojsonByGroup(name: String): Flux<Geojson> {
        logger.info("Service connecting to geojson repository to get geojsons with name: ${name}.")
        return geojsonRepository.getGeojsonByName(name);
    }
}