package com.example.blo.domain.account.service.exception

import com.example.blo.global.error.CustomException
import com.example.blo.global.error.ErrorCode

object AccountNoPermissionException : CustomException(ErrorCode.ACCOUNT_NO_PERMISSION)