package si.um.feri.gisgeodataservice.services

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import si.um.feri.gisgeodataservice.models.FeatureCollection
import si.um.feri.gisgeodataservice.repository.FeatureCollectionRepository

@Service
class FeatureCollectionService(var featureCollectionRepository: FeatureCollectionRepository) {
    fun getAllFeatureCollections(): Flux<FeatureCollection> {
        return featureCollectionRepository.findAll()
    }
}