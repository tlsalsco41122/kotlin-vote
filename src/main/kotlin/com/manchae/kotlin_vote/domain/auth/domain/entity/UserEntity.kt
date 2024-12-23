package com.manchae.kotlin_vote.domain.auth.domain.entity

import com.manchae.kotlin_vote.domain.auth.domain.enum.UserRoles
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class UserEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val username: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val role: UserRoles = UserRoles.ROLE_USER
)