package com.pulpos.quotes.repository

import com.pulpos.quotes.model.Quote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuoteRepository : JpaRepository<Quote, Long>
