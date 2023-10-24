package com.example.blo.global.configuration

import com.example.blo.global.security.jwt.filter.TokenFilter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class FilterConfiguration(
    private val tokenFilter: TokenFilter
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {

    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}