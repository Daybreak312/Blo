package com.example.blo.global.security.service.exception

import com.example.blo.global.error.CustomException
import com.example.blo.global.error.ErrorCode

object NotBearerTokenException : CustomException(ErrorCode.NOT_BEARER_TOKEN)