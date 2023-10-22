package com.example.blo.global.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpStatus: HttpStatus, val message: String
) {
    // Account
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없는 계정입니다."),

    // Auth
    SECURITY_CONTEXT_EMPTY(HttpStatus.NOT_FOUND, "시큐리티 컨텍스트가 비어있습니다.")
}