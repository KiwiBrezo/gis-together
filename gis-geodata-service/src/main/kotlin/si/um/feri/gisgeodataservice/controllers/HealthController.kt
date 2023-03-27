package si.um.feri.gisgeodataservice.controllers

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class HealthController {

    private var logger: Logger = LoggerFactory.getLogger(HealthController::class.java)

    @GetMapping("/ping")
    fun pong(): ResponseEntity<String>
    {
        logger.info("Connecting to Health Controller for Pong.")
        return ResponseEntity("Pong", HttpStatusCode.valueOf(200))
    }

}