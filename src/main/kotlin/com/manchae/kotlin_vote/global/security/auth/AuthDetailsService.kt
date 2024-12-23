package com.manchae.kotlin_vote.global.security.auth

import com.manchae.kotlin_vote.domain.auth.domain.AuthRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthDetailsService(
    private val authRepository: AuthRepository
): UserDetailsService {

    @Transactional
    override fun loadUserByUsername(email: String?): UserDetails {

    }
}