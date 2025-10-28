package dev.killercavin.fortspringauthservice.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("user_roles")
data class UserRole(
    @Column("user_id") val userId: Long,
    @Column("user_role") val roleId: Long,
    @CreatedDate @Column("created_at") val createdAt: Instant? = null,
    @LastModifiedDate @Column("updated_at") val updatedAt: Instant? = null
)
