package com.example.blo.global.error

open class CustomException(errorCode: ErrorCode) : RuntimeException(errorCode.message)