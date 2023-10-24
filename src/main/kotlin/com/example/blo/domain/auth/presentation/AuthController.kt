package com.example.blo.domain.auth.presentation

import com.example.blo.domain.auth.presentation.dto.request.LoginRequest
import com.example.blo.domain.auth.presentation.dto.request.SignRequest
import com.example.blo.domain.auth.port.`in`.AuthUsecase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("/auth")
@RestController
class AuthController(
    private val authService: AuthUsecase
) {

    @PostMapping("/sign")
    @ResponseStatus(HttpStatus.CREATED)
    fun sign(@Valid @RequestBody request: SignRequest) =
        authService.sign(request)

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginRequest) =
        authService.login(request)
}