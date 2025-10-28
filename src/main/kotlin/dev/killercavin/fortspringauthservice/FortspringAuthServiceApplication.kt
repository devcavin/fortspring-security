package dev.killercavin.fortspringauthservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing

@EnableJdbcAuditing
@SpringBootApplication
class FortspringAuthServiceApplication

fun main(args: Array<String>) {
    runApplication<FortspringAuthServiceApplication>(*args)
}
