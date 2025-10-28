package dev.killercavin.fortspringauthservice.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("users")
data class User(
    @Id val id: Long?,
    @field:NotBlank(message = "Full name cannot be blank") @Column("full_name")
    val fullName: String,

    @field:NotBlank(message = "Username cannot be blank")
    val username: String,

    @field:Email(message = "Invalid email format")
    val email: String,

    @field:NotBlank(message = "Password cannot be blank") @get:JsonIgnore @Column("hashed_password")
    val hashedPassword: String,

    @CreatedDate @Column("created_at")
    val createdAt: Instant? = null,

    @LastModifiedDate @Column("updated_at")
    val updatedAt: Instant? = null
)
