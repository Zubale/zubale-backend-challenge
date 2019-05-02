package com.pulpos.quotes

import com.pulpos.quotes.model.Quote
import com.pulpos.quotes.repository.QuoteRepository
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import java.util.*
import javax.validation.Valid
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("/api/quotes")
class QuotesController(private val quoteRepository: QuoteRepository) {
    @GetMapping("/")
    fun getAllQuotes() : List<Quote> =
        quoteRepository.findAll()

    @PostMapping("/submit")
    fun submit(@RequestParam quoteText : String) : String {
        return "Not implemented yet"
    }

    @GetMapping("/voteup")
    fun voteUp(@RequestParam(name = "id") quoteId : Int) : String {
        return "Not implemented yet"
    }

    @GetMapping("/votedown")
    fun voteDown(@RequestParam(name = "id") quoteId : Int) : String {
        return "Not implemented yet"
    }

    @GetMapping("/list")
    fun getQuotePage(@RequestParam page : Int, @RequestParam pageSize : Int) : String {
        return "Not inplemented yet"
    }


}