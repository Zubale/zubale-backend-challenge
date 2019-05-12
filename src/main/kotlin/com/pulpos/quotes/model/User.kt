package com.pulpos.quotes.model


import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class User (
    private var username: String = "",

    @JsonIgnore
    private var password: String = ""
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    var credentialsNonExpired: Boolean = true

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    val quotes: MutableSet<Quote> = HashSet()

    constructor(user: User) : this(user.password, user.username) {
        this.username = user.username
        this.password = user.password
        this.credentialsNonExpired = user.credentialsNonExpired
    }
    fun getUsername(): String{
        return username
    }
    fun getPassword(): String{
        return password
    }
}

