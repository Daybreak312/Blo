package com.example.blo.global.logger

import org.slf4j.Logger

interface LoggerInterface {
    val loggerTypeTag: LoggerTypeTag
    val logger: Logger
}