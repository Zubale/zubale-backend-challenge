package com.pulpos.quotes.model


import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val username: String,

    @JsonIgnore
    val password: String
) {
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    val quotes: MutableSet<Quote> = HashSet()
}