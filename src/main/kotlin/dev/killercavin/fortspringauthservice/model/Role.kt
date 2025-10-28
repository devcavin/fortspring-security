package dev.killercavin.fortspringauthservice.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("roles")
data class Role(
    @Id val id: Long?,
    @Column("name") val name: String,
    @CreatedDate @Column("created_at") val createdAt: Instant? = null,
    @LastModifiedDate @Column("updated_at") val updatedAt: Instant? = null
)
