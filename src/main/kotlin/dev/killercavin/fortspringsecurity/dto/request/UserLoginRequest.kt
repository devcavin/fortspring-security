package dev.killercavin.fortspringsecurity.dto.request

data class UserLoginRequest(
    val email: String,
    val password: String
)
