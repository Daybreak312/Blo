package com.example.blo.global.security.service.exception

import com.example.blo.global.error.CustomException
import com.example.blo.global.error.ErrorCode

object TokenWithoutPrefixException : CustomException(ErrorCode.TOKEN_WITHOUT_PREFIX)