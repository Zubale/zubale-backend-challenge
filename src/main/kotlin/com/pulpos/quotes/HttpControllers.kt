package com.pulpos.quotes

import com.pulpos.quotes.model.Quote
import com.pulpos.quotes.model.User
import com.pulpos.quotes.model.Vote
import com.pulpos.quotes.repository.QuoteRepository
import com.pulpos.quotes.repository.UserRepository
import com.pulpos.quotes.repository.VoteRepository
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import java.time.LocalDate
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.authentication.AnonymousAuthenticationToken





@RestController
@RequestMapping("/api/quotes")
class QuotesController(private val quoteRepository: QuoteRepository, private val userRepository: UserRepository, private val voteRepository: VoteRepository) {
    @GetMapping("/")
    fun getAllQuotes() : List<Quote> =
        quoteRepository.findAll()

    @PostMapping("/submit")
    fun submit(@Valid @RequestBody quote : Quote) : Quote {
        val username = currentUsername()
        val user : User = userRepository.findOneByUsername(username)!!
        val author = if (quote.author.isBlank()) user.getUsername() else quote.author
        val quoteToSave = Quote(text = quote.text, author =  author, date = LocalDate.now(), user = user)

        return quoteRepository.save(quoteToSave)
    }

    

    @GetMapping("/voteup")
    fun voteUp(@RequestParam(name = "id") quoteId : Long) : String {
        val username = currentUsername()
        val quote : Quote = quoteRepository.findById(quoteId).get()
        val user : User = userRepository.findOneByUsername(username)!!
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
        val username = currentUsername()
        val user : User = userRepository.findOneByUsername(username)!!
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
        val username = currentUsername()
        val user : User = userRepository.findOneByUsername(username)!!
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
        return "Not implemented yet"
    }


    fun currentUsername(): String{
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication !is AnonymousAuthenticationToken) {
            return authentication.name
        }else{
            return ""
        }
    }

}