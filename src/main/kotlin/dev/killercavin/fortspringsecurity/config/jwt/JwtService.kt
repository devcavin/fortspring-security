package dev.killercavin.fortspringsecurity.config.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Base64
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtService(
    @param:Value("404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970") private val jwtSecret: String
) {
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret)) // secret key signing
    private val accessTokenValidity = 1000L * 60L * 15L // access token is valid for 15 minutes
    private val refreshTokenValidity = 1000L * 60L * 60L * 24 // refresh token is valid for 24 hours

    // generate tokens
    private fun tokenGeneration(
        userId: String,
        type: String,
        expiry: Long
    ): String {
        val timeIssued = Date()
        val expiryTime = Date(timeIssued.time + expiry)

        return Jwts.builder()
            .subject(userId)
            .claim("type", type)
            .issuedAt(timeIssued)
            .expiration(expiryTime)
            .signWith(secretKey, Jwts.SIG.HS256)
            .compact()
    }

    fun generateAccessToken(userId: String): String {
        return tokenGeneration(userId = userId, type = "access", expiry = accessTokenValidity)
    }

    fun generateRefreshToken(userId: String): String {
        return tokenGeneration(userId = userId, type = "refresh", expiry = refreshTokenValidity)
    }

    // extract and parse all JWT claims from the header information
    fun parseAllClaims(token: String): Claims? {
        val rawToken = if (token.startsWith("Bearer ")) token.removePrefix("Bearer ") else token

        return try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(rawToken)
                .payload
        } catch (e: Exception) {
            null
        }
    }

    // validate tokens
    fun validateAccessToken(token: String): Boolean {
        val claims = parseAllClaims(token) ?: return false
        val tokenType = claims["type"] as? String ?: return false
        return tokenType == "access"
    }

    fun validateRefreshToken(token: String): Boolean {
        val claims = parseAllClaims(token) ?: return false
        val tokenType = claims["type"] as? String ?: return false
        return tokenType == "refresh"
    }

    fun getUserIdFromToken(token: String): String {
        val claims = parseAllClaims(token) ?: throw IllegalArgumentException("Invalid token")
        return claims.subject
    }
}