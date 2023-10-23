package com.example.blo.global.security.service.exception

import com.example.blo.global.error.CustomException
import com.example.blo.global.error.ErrorCode

object ExpiredTokenException : CustomException(ErrorCode.EXPIRED_TOKEN)