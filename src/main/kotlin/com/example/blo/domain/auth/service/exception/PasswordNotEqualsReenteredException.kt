package com.example.blo.domain.auth.service.exception

import com.example.blo.global.error.CustomException
import com.example.blo.global.error.ErrorCode

object PasswordNotEqualsReenteredException : CustomException(ErrorCode.PASSWORD_NOT_EQUALS_REENTERED)