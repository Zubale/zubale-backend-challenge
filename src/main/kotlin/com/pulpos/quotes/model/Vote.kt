package com.pulpos.quotes.model

import javax.persistence.*

@Entity
data class Vote(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @OneToOne val quote: Quote,

    @OneToOne val user: User,

    var positiveVote : Boolean

)