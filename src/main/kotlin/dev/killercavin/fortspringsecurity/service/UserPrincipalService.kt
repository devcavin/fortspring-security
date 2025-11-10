package dev.killercavin.fortspringsecurity.service

import dev.killercavin.fortspringsecurity.repository.UserRepository
import dev.killercavin.fortspringsecurity.config.UserPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserPrincipalService(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findUserByEmail(username) ?: throw UsernameNotFoundException("User not found with username: $username")

        return UserPrincipal(user)
    }
}
