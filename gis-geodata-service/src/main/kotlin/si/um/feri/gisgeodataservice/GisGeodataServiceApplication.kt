package si.um.feri.gisgeodataservice

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import si.um.feri.gisgeodataservice.repository.GeojsonRepository

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackageClasses = [GeojsonRepository::class])
@OpenAPIDefinition(info = Info(title = "Swagger gis-geodata API",
							   version = "1.0",
							   description = "List of exposed endpoint and how-to use it."))
class GisGeodataServiceApplication

fun main(args: Array<String>) {
	runApplication<GisGeodataServiceApplication>(*args)
}
