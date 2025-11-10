package dev.killercavin.fortspringsecurity.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    val id: Long? = null,

    @Column(name = "firstname", nullable = false, columnDefinition = "TEXT")
    val firstName: String,

    @Column(name = "lastname", nullable = false, columnDefinition = "TEXT")
    val lastName: String,

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    @field:Email
    val email: String,

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    val hashedPassword: String,

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    val role: Role? = Role.USER,

    @CreationTimestamp
    @Column(name = "createdat")
    val createdAt: Instant? = null,

    @UpdateTimestamp
    @Column(name = "updatedat")
    val updatedAt: Instant? = null
)
