package dev.killercavin.fortspringsecurity.dto.response

import dev.killercavin.fortspringsecurity.entity.Role
import dev.killercavin.fortspringsecurity.entity.User
import java.time.Instant

data class UserResponse(
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: Role?,
    val createdAt: Instant?,
    val updatedAt: Instant?
)

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        role = this.role,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}
