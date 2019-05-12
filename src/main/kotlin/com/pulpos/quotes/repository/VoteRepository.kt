package com.pulpos.quotes.repository

import com.pulpos.quotes.model.Quote
import com.pulpos.quotes.model.User
import com.pulpos.quotes.model.Vote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface VoteRepository : JpaRepository<Vote, Long> {
    fun findByUserAndQuote(user: User, quote: Quote) : Optional<Vote>
}