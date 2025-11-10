package dev.killercavin.fortspringsecurity.config.jwt

import dev.killercavin.fortspringsecurity.config.UserPrincipal
import dev.killercavin.fortspringsecurity.repository.UserRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtService: JwtService,
    private val userRepository: UserRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val authHeader = request.getHeader("Authorization")
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                val token = authHeader.removePrefix("Bearer ").trim()

                // Validate token
                if (jwtService.validateAccessToken(token)) {

                    // Extract userId from token
                    val userId = jwtService.getUserIdFromToken(token)

                    // Load user from DB
                    val user = userRepository.findById(userId.toLong())
                        .orElse(null)

                    if (user != null) {
                        val principal = UserPrincipal(user)

                        val auth = UsernamePasswordAuthenticationToken(
                            principal,
                            null,
                            principal.authorities
                        )

                        SecurityContextHolder.getContext().authentication = auth
                    }
                }
            }
        } catch (ex: Exception) {
            // log exceptions
            logger.error("JWT authentication failed: ${ex.message}")
        }

        // Proceed with the filter chain
        filterChain.doFilter(request, response)
    }
}
