package com.example.blo.domain.account.exception

import com.example.blo.global.error.CustomException
import com.example.blo.global.error.ErrorCode

object ValidationFailException : CustomException(ErrorCode.VALIDATION_FAIL)