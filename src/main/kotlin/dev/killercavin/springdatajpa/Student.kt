package dev.killercavin.springdatajpa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "student")
data class Student(
    @Id @Column(name = "id", updatable = false) @SequenceGenerator( name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1) @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence") val id: Long? = null,
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT") val firstName: String,
    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT") val lastName: String,
    @field:Email @Column(name = "email", nullable = false, columnDefinition = "TEXT", unique = true) val email: String,
    @Column(name = "age", nullable = false) val age: Int,
    @Column(name = "created_at") @CreationTimestamp val createdAt: Instant? = null,
    @Column(name = "updated_at") @UpdateTimestamp val updatedAt: Instant? = null
    )