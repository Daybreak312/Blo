package com.example.blo.domain.blog.service.exception

import com.example.blo.global.error.CustomException
import com.example.blo.global.error.ErrorCode

object BlogNoPermissionException : CustomException(ErrorCode.BLOG_NO_PERMISSION)