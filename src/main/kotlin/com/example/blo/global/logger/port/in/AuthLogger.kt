package com.example.blo.global.logger.port.`in`

interface AuthLogger {
    fun signupLog(accountId: String)
    fun loginLog(accountId: String)
    fun logoutLog(accountId: String)
}