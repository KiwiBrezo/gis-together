package si.um.feri.gisgeodataservice.repository

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import si.um.feri.gisgeodataservice.models.FeatureCollection

@Repository
interface FeatureCollectionRepository : ReactiveMongoRepository<FeatureCollection, String> {
}