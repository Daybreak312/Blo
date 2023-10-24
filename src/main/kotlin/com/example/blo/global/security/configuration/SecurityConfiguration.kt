package com.example.blo.global.security.configuration

import com.example.blo.global.configuration.FilterConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val filterConfiguration: FilterConfiguration
) {

    @Bean
    fun getPasswordEncoder(): PasswordEncoder =
        PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    protected fun config(httpSecurity: HttpSecurity): SecurityFilterChain =
        httpSecurity
            .formLogin().disable()
            .csrf().disable()
            .cors().and()

            .exceptionHandling()
            .and()

            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

            .authorizeRequests()
            .antMatchers("/auth/**").permitAll()
            .anyRequest().authenticated()
            .and()

            .apply(filterConfiguration)
            .and()

            .build()
}