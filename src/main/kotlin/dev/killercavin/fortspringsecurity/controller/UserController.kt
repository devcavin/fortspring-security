package dev.killercavin.fortspringsecurity.controller

import dev.killercavin.fortspringsecurity.config.UserPrincipal
import dev.killercavin.fortspringsecurity.dto.response.UserResponse
import dev.killercavin.fortspringsecurity.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/user")
class UserController(private val userService: UserService) {
    private val logger = LoggerFactory.getLogger(UserService::class.java)
    // user profile
    // using spring security filter
    @GetMapping("/profile")
    fun getUserProfile(@AuthenticationPrincipal principal: UserPrincipal?): ResponseEntity<UserResponse> {
        val username = principal?.username ?: throw ResponseStatusException(
            HttpStatus.UNAUTHORIZED,
            "User not authenticated"
        )

        val userProfile = userService.getUserProfileByEmail(username)

        logger.info("Profile request by email ${userProfile.email}")
        return ResponseEntity.ok(userProfile)
    }
}