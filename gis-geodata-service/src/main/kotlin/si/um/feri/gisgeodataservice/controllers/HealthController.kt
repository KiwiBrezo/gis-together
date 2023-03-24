package si.um.feri.gisgeodataservice.controllers

import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class HealthController {

    @GetMapping("/ping")
    fun pong(): ResponseEntity<String>
    {
        return ResponseEntity("Pong", HttpStatusCode.valueOf(200))
    }

}