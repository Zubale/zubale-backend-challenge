package com.pulpos.quotes.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Quote (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @get: NotBlank
        val text: String = "",

        val date: LocalDate,

        val votes: Long = 0,

        @ManyToOne val author: User,

        val postedBy: String

)
