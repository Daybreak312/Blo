package com.example.blo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class BloApplication

fun main(args: Array<String>) {
    runApplication<BloApplication>(*args)
}
