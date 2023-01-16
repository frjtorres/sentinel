package com.sandbox.sentinel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

/*
* TODO: validar el paso de builder a metodo para exceptions
* TODO: agregar y configurar logs con fecha y hora UTC
*/

@SpringBootApplication(exclude = [ SecurityAutoConfiguration::class ])
class SentinelApplication

fun main(args: Array<String>) {
	runApplication<SentinelApplication>(*args)
}
