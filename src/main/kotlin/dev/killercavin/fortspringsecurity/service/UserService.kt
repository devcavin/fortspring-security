package dev.killercavin.fortspringsecurity.service

import dev.killercavin.fortspringsecurity.config.jwt.JwtService
import dev.killercavin.fortspringsecurity.dto.response.UserResponse
import dev.killercavin.fortspringsecurity.exception.ResourceNotFoundException
import dev.killercavin.fortspringsecurity.repository.UserRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service

@Service
class UserService(private val jwtService: JwtService, private val userRepository: UserRepository) {
    fun getUserProfileByEmail(token: String): UserResponse {
        if (!jwtService.validateAccessToken(token)) throw BadCredentialsException("Invalid token")
        val userId = jwtService.getUserIdFromToken(token)
        val user = userRepository.findById(userId.toLong())
            .orElseThrow { ResourceNotFoundException("User not found") }

        return UserResponse(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            role = user.role,
            createdAt = user.createdAt,
            updatedAt = user.updatedAt
        )
    }
}
