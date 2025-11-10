package dev.killercavin.fortspringsecurity.controller

import dev.killercavin.fortspringsecurity.dto.request.CreateUserRequest
import dev.killercavin.fortspringsecurity.dto.request.UserLoginRequest
import dev.killercavin.fortspringsecurity.dto.response.UserResponse
import dev.killercavin.fortspringsecurity.service.AuthService
import dev.killercavin.fortspringsecurity.service.TokenPair
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(private val authService: AuthService) {
    private val logger = LoggerFactory.getLogger(AuthController::class.java)

    // register
    @PostMapping("/register")
    fun register(@Validated @RequestBody request: CreateUserRequest): ResponseEntity<UserResponse> {
        logger.info("Register attempt for email ${request.email}")
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(request))
    }

    // login
    @PostMapping("/login")
    fun login(@Validated @RequestBody request: UserLoginRequest): ResponseEntity<TokenPair> {
        logger.info("Login attempt by email ${request.email}")
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.loginUser(request))
    }
}