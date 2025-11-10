package dev.killercavin.fortspringsecurity.service

import dev.killercavin.fortspringsecurity.config.jwt.JwtService
import dev.killercavin.fortspringsecurity.dto.request.CreateUserRequest
import dev.killercavin.fortspringsecurity.dto.request.UserLoginRequest
import dev.killercavin.fortspringsecurity.dto.request.toEntity
import dev.killercavin.fortspringsecurity.dto.response.UserResponse
import dev.killercavin.fortspringsecurity.dto.response.toResponse
import dev.killercavin.fortspringsecurity.exception.DuplicateUniqueFieldException
import dev.killercavin.fortspringsecurity.exception.ResourceNotFoundException
import dev.killercavin.fortspringsecurity.repository.UserRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

data class TokenPair(
    val accessToken: String,
    val refreshToken: String
)

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) {
    // register user
    fun registerUser(request: CreateUserRequest): UserResponse {
        // check if user exists
        val userExists = userRepository.existsUsersByEmail(request.email)

        if (userExists) throw DuplicateUniqueFieldException(message = "User already exists")

        return userRepository.save(request.toEntity(passwordEncoder)).toResponse()
    }

    // login user
    fun loginUser(request: UserLoginRequest): TokenPair {
        // find user by email
        val user = userRepository.findUserByEmail(request.email) ?: throw ResourceNotFoundException("User not found")

        // validate password
        if (!passwordEncoder.matches(request.password, user.hashedPassword)) throw BadCredentialsException("Incorrect password")

        // generate tokens
        val newAccessToken = jwtService.generateAccessToken(user.id.toString())
        val newRefreshToken = jwtService.generateAccessToken(user.id.toString())

        // return the tokens
        return TokenPair(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken
        )
    }
}