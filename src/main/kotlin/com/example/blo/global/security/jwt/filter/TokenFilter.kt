package com.example.blo.global.security.jwt.filter

import com.example.blo.domain.account.entity.Account
import com.example.blo.domain.account.service.exception.AccountNotFoundException
import com.example.blo.global.security.jwt.env.JwtProperty
import com.example.blo.global.security.jwt.port.`in`.TokenResolveUsecase
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TokenFilter(
    private val tokenResolver: TokenResolveUsecase,
    private val loadAccountService: UserDetailsService,
    private val jwtProperty: JwtProperty
) : OncePerRequestFilter() {

    private val SKIP_AUTHENTICATION_PATHS = arrayListOf("/auth")

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (checkNeedAuthenticationUrl(request)) {
            val authenticationHeader = getAuthenticationHeader(request)
            val accountId = tokenResolver.resolveTokenToAccountId(authenticationHeader)
            val account = findAccountByAccountId(accountId) ?: throw AccountNotFoundException
            saveInSecurityContext(account)
        }

        doFilter(request, response, filterChain)
    }

    private fun checkNeedAuthenticationUrl(request: HttpServletRequest) =
        !SKIP_AUTHENTICATION_PATHS.any { request.requestURI.startsWith(it) }

    private fun getAuthenticationHeader(request: HttpServletRequest) =
        request.getHeader(jwtProperty.header)

    private fun findAccountByAccountId(accountId: String): Account? =
        loadAccountService.loadUserByUsername(accountId) as Account?

    private fun saveInSecurityContext(account: Account) {
        SecurityContextHolder.getContext().authentication =
            UsernamePasswordAuthenticationToken(
                account.accountId, "", mutableListOf(GrantedAuthority { account.role.name })
            )
    }
}