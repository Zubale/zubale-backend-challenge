package com.pulpos.quotes.security

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableConfigurationProperties
class SecurityConfiguration(private val customUserDetailsService: CustomUserDetailsService,
                            private val passwordEncoderAndMatcher: PasswordEncoder)
    : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.authorizeRequests().antMatchers("/api/quotes/").permitAll()
        http.authorizeRequests().antMatchers("/api/quotes/list").permitAll()
        http.authorizeRequests()
                .antMatchers("/api/quotes/*").authenticated()
                .anyRequest().permitAll()
                .and().httpBasic()

    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoderAndMatcher)
    }
}