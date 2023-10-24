package com.example.blo.global.logger.impl

import com.example.blo.env.LogEnv
import com.example.blo.global.logger.LoggerTypeTag
import com.example.blo.global.logger.port.`in`.AuthLogger
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class AuthLoggerImpl : AuthLogger {

    val logger: Logger = LoggerFactory.getLogger(LogEnv.customLoggerName)
    val loggerTypeTag: LoggerTypeTag = LoggerTypeTag.AUTHENTICATION

    override fun signupLog(accountId: String) {
        logger.info("$loggerTypeTag $accountId was sign")
    }

    override fun loginLog(accountId: String) {
        logger.info("$loggerTypeTag $accountId was login")
    }

    override fun logoutLog(accountId: String) {
        logger.info("$loggerTypeTag $accountId was logout")
    }
}