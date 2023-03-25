package si.um.feri.gisgeodataservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import si.um.feri.gisgeodataservice.controllers.GeojsonController
import si.um.feri.gisgeodataservice.controllers.HealthController

@SpringBootTest(classes = [GisGeodataServiceApplication::class],
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GisGeodataServiceApplicationTests(@Autowired private var geojsonController: GeojsonController, @Autowired private var healthController: HealthController) {

	@Autowired
	lateinit var restTemplate: TestRestTemplate

	@Test
	fun contextLoads() {
		Assertions.assertNotNull(geojsonController)
		Assertions.assertNotNull(healthController)
	}

	@Test
	fun testPingResponse() {
		val result = restTemplate.getForEntity("/api/v1/ping", String::class.java);

		Assertions.assertNotNull(result)
		Assertions.assertEquals(HttpStatus.OK, result?.statusCode)
		Assertions.assertEquals("Pong", result.getBody().orEmpty())
	}

}
