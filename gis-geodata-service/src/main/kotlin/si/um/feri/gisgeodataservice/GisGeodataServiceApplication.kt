package si.um.feri.gisgeodataservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import si.um.feri.gisgeodataservice.repository.GeojsonRepository

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackageClasses = [GeojsonRepository::class])
class GisGeodataServiceApplication

fun main(args: Array<String>) {
	runApplication<GisGeodataServiceApplication>(*args)
}
