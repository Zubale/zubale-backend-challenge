package com.pulpos.quotes

import com.pulpos.quotes.model.Quote
import com.pulpos.quotes.model.User
import com.pulpos.quotes.repository.QuoteRepository
import com.pulpos.quotes.repository.UserRepository
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import java.util.*
import javax.validation.Valid
import org.springframework.http.HttpStatus
import java.time.LocalDate

@RestController
@RequestMapping("/api/quotes")
class QuotesController(private val quoteRepository: QuoteRepository, private val userRepository: UserRepository) {
    @GetMapping("/")
    fun getAllQuotes() : List<Quote> =
        quoteRepository.findAll()

    @PostMapping("/submit")
    fun submit(@Valid @RequestBody quote : Quote) : Quote {
        val user : User = userRepository.findById(1).get() //TODO: This user should be obtained using authentication
        val author = if (quote.author.isBlank()) user.getUsername() else quote.author
        val quoteToSave = Quote(text = quote.text, author =  author, date = LocalDate.now(), user = user)

        return quoteRepository.save(quoteToSave)
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