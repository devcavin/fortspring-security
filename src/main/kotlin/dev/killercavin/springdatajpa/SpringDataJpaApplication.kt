package dev.killercavin.springdatajpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringDataJpaApplication

fun main(args: Array<String>) {
    runApplication<SpringDataJpaApplication>(*args)
}
