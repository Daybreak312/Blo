package com.example.blo.global.security.service.exception

import com.example.blo.global.error.CustomException
import com.example.blo.global.error.ErrorCode

object NotJwtTokenException : CustomException(ErrorCode.NOT_JWT_TOKEN)