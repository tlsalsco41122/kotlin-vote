package com.manchae.kotlin_vote.domain.auth.domain

import com.manchae.kotlin_vote.domain.auth.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AuthRepository: JpaRepository<UserEntity, String> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): UserEntity?
}