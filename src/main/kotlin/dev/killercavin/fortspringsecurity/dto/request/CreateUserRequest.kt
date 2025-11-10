package dev.killercavin.fortspringsecurity.dto.request

import dev.killercavin.fortspringsecurity.entity.Role
import dev.killercavin.fortspringsecurity.entity.User
import org.springframework.security.crypto.password.PasswordEncoder

data class CreateUserRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val role: Role? = Role.USER
)


fun CreateUserRequest.toEntity(passwordEncoder: PasswordEncoder): User {
    return User(
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        hashedPassword = passwordEncoder.encode(this.password),
        role = this.role
    )
}
