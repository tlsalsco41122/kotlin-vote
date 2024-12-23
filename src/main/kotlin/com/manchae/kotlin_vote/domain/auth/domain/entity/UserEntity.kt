package com.manchae.kotlin_vote.domain.auth.domain.entity

import com.manchae.kotlin_vote.domain.auth.domain.enums.UserType
import jakarta.persistence.*

@Entity
@Table(name = "tb_user")
class UserEntity (

    @Id
    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val role: UserType = UserType.ROLE_USER
)