package com.pulpos.quotes.security

import com.pulpos.quotes.model.User
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.HashSet



class CustomUserDetails : User, UserDetails {

    private val log = LoggerFactory.getLogger(CustomUserDetails::class.java)

    constructor(user: User) : super(user)

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return HashSet()
    }
    override fun getUsername(): String {
        return super.getUsername()
    }

    override fun getPassword(): String {
        return super.getPassword()
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return super.credentialsNonExpired
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}