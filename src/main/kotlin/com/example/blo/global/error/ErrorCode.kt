package com.example.blo.global.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpStatus: HttpStatus, val message: String
) {
    // Account
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없는 계정입니다."),

    // Auth
    SECURITY_CONTEXT_EMPTY(HttpStatus.NOT_FOUND, "시큐리티 컨텍스트가 비어있습니다."),
    IN_USE_ACCOUNT_ID(HttpStatus.CONFLICT, "사용하려는 아이디가 이미 사용중인 아이디입니다."),
    PASSWORD_NOT_EQUALS_REENTERED(HttpStatus.BAD_REQUEST, "비밀번호와 재입력한 비밀번호가 다릅니다."),
    PASSWORD_MISMATCH(HttpStatus.FORBIDDEN, "로그인하려는 계정과 비밀번호가 일치하지 않습니다."),

    // Token
    TOKEN_WITHOUT_PREFIX(HttpStatus.BAD_REQUEST, "토큰이 정해진 접두사를 가지고 있지 않습니다."),
    NOT_JWT_TOKEN(HttpStatus.BAD_REQUEST, "JWT 토큰이 아닙니다."),
    EXPIRED_TOKEN(HttpStatus.FORBIDDEN, "만료된 토큰입니다.")
}