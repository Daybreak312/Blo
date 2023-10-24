package com.example.blo.domain.account.service.exception

import com.example.blo.global.error.CustomException
import com.example.blo.global.error.ErrorCode

object AccountNotFoundException : CustomException(ErrorCode.ACCOUNT_NOT_FOUND)