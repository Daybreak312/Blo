package com.example.blo.global.security.auth.exception

import com.example.blo.global.error.CustomException
import com.example.blo.global.error.ErrorCode

object SecurityContextEmptyException : CustomException(ErrorCode.SECURITY_CONTEXT_EMPTY)