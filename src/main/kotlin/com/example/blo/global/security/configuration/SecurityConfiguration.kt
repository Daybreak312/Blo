package com.example.blo.global.security.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Suppress("DEPRECATION")
@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    fun getPasswordEncoder(): PasswordEncoder =
        PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun config(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .formLogin().disable()
            .csrf().and()
            .cors().and()

            .exceptionHandling()
            .and()

            .build()
    }
}