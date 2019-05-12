package com.pulpos.quotes

import com.pulpos.quotes.model.Quote
import com.pulpos.quotes.model.User
import com.pulpos.quotes.model.Vote
import com.pulpos.quotes.repository.QuoteRepository
import com.pulpos.quotes.repository.UserRepository
import com.pulpos.quotes.repository.VoteRepository
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import java.util.*
import javax.validation.Valid
import org.springframework.http.HttpStatus
import java.time.LocalDate

@RestController
@RequestMapping("/api/quotes")
class QuotesController(private val quoteRepository: QuoteRepository, private val userRepository: UserRepository, private val voteRepository: VoteRepository) {
    @GetMapping("/")
    fun getAllQuotes() : List<Quote> =
        quoteRepository.findAll()

    @PostMapping("/submit")
    fun submit(@Valid @RequestBody quote : Quote) : Quote {
        val user : User = userRepository.findById(1).get() //TODO: This user should be obtained using authentication
        val author = if (quote.author.isBlank()) user.username else quote.author
        val quoteToSave = Quote(text = quote.text, author =  author, date = LocalDate.now(), user = user)

        return quoteRepository.save(quoteToSave)
    }

    

    @GetMapping("/voteup")
    fun voteUp(@RequestParam(name = "id") quoteId : Long) : String {
        val quote : Quote = quoteRepository.findById(quoteId).get()
        val user : User = userRepository.findById(2).get() //TODO: This user should be obtained using authentication
        val optionalVote = voteRepository.findByUserAndQuote(user, quote)

        if(optionalVote.isPresent) {
           val vote = optionalVote.get()
            if(!vote.positiveVote) {
                vote.positiveVote = true
                voteRepository.save(vote)
                quote.votes += 2
                quoteRepository.save(quote)
            }
        } else {
            val vote = Vote(quote = quote, user = user, positiveVote = true)
            voteRepository.save(vote)
            quote.votes += 1
            quoteRepository.save(quote)
        }
        return quote.votes.toString()
    }

    @GetMapping("/votedown")
    fun voteDown(@RequestParam(name = "id") quoteId : Long) : String {
        val quote : Quote = quoteRepository.findById(quoteId).get()
        val user : User = userRepository.findById(2).get() //TODO: This user should be obtained using authentication
        val optionalVote = voteRepository.findByUserAndQuote(user, quote)

        if(optionalVote.isPresent) {
            val vote = optionalVote.get()
            if(vote.positiveVote) {
                vote.positiveVote = false
                voteRepository.save(vote)
                quote.votes -= 2
                quoteRepository.save(quote)
            }
        } else {
            val vote = Vote(quote = quote, user = user, positiveVote = false)
            voteRepository.save(vote)
            quote.votes -= 1
            quoteRepository.save(quote)
        }
        return quote.votes.toString()
    }

    @GetMapping("/removevote")
    fun removeVote(@RequestParam(name = "id") quoteId : Long) : String {
        val quote : Quote = quoteRepository.findById(quoteId).get()
        val user : User = userRepository.findById(2).get() //TODO: This user should be obtained using authentication
        val optionalVote = voteRepository.findByUserAndQuote(user, quote)
        if(optionalVote.isPresent) {
            val vote = optionalVote.get()
            if (vote.positiveVote) {
                quote.votes -= 1
            } else {
                quote.votes += 1
            }
            voteRepository.delete(vote)
            quoteRepository.save(quote)
        }

        return quote.votes.toString()
    }


    @GetMapping("/list")
    fun getQuotePage(@RequestParam page : Int, @RequestParam pageSize : Int) : String {
        return "Not inplemented yet"
    }


}