package com.example.blo.domain.account.port.`in`

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

interface CustomUserDetailsUsecase : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails?
}