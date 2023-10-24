package com.example.blo.domain.auth.service.exception

import com.example.blo.global.error.CustomException
import com.example.blo.global.error.ErrorCode

object PasswordMismatchException : CustomException(ErrorCode.PASSWORD_MISMATCH)