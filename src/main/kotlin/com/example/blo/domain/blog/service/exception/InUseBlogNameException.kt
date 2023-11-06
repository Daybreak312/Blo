package com.example.blo.domain.blog.service.exception

import com.example.blo.global.error.CustomException
import com.example.blo.global.error.ErrorCode

object InUseBlogNameException : CustomException(ErrorCode.IN_USE_BLOG_NAME)