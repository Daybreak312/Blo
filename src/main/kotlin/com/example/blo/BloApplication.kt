package com.example.blo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = ["com.example"])
@ConfigurationPropertiesScan
@SpringBootApplication
class BloApplication

fun main(args: Array<String>) {
    runApplication<BloApplication>(*args)
}