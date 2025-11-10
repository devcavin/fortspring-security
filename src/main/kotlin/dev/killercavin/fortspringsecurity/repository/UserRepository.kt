package dev.killercavin.fortspringsecurity.repository

import dev.killercavin.fortspringsecurity.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findUserByEmail(email: String): User?
    fun existsUsersByEmail(email: String): Boolean
}