package com.pulpos.quotes

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QuotesController {
    @GetMapping("/")
    fun helloWorld() : String {
        return "Hello world"
    }
}