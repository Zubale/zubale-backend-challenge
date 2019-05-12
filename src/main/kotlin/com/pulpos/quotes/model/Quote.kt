package com.pulpos.quotes.model

import com.fasterxml.jackson.annotation.*
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Quote (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @get: NotBlank
        var text: String = "",

        val date: LocalDate? = null,

        var votes: Long = 0,

        var author: String = ""

) {

        @ManyToOne
        lateinit var user: User

        constructor(text: String, date: LocalDate, author: String, user : User) : this(text = text, date = date, author = author) {
                this.user = user
        }
}
