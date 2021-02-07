package it.peluso.ecommerce

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("/")
class GetTest {
    @GetMapping
    fun helloWorld(@RequestParam(required = false) name: String?): ResponseEntity<String> {
        return ResponseEntity("Hello " + (name ?: "world") + "!", HttpStatus.OK)
    }
}
