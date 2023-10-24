package com.example.blo.domain.auth.service.exception

import com.example.blo.global.error.CustomException
import com.example.blo.global.error.ErrorCode

object InUseAccountIdException: CustomException(ErrorCode.IN_USE_ACCOUNT_ID)