package com.example.blo.domain.auth.presentation.dto.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class SignRequest(

    @Pattern(regexp = "^([\\w\\s가-힣]){1,30}", message = "이름은 한글, 영어, 숫자로 이루어져야 합니다.")
    val name: String,

    @Pattern(regexp = "^(\\w){5,30}", message = "아이디는 영어, 숫자로 이루어져야 하며, 5자 이상, 100자 이하여야 합니다.")
    val accountId: String,

    @Pattern(regexp = "^(?=.*[!@#$%^&*]){5,30}", message = "")
    val password: String,

    @Pattern(regexp = "^(?=.*[!@#$%^&*]){5,30}")
    val reenteredPassword: String
)