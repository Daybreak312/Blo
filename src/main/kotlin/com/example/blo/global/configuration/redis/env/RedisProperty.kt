package com.example.blo.global.configuration.redis.env

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.redis")
data class RedisProperty(
    val host: String,
    val port: Int
)