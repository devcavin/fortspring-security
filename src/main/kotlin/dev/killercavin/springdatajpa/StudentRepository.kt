package dev.killercavin.springdatajpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface StudentRepository: JpaRepository<Student, Long> {
}