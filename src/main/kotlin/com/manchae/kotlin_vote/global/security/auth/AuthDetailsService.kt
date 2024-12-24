package com.manchae.kotlin_vote.global.security.auth

import com.manchae.kotlin_vote.domain.auth.domain.AuthRepository
import com.manchae.kotlin_vote.domain.auth.exception.UserErrorCode
import com.manchae.kotlin_vote.global.exception.CustomException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthDetailsService(
    private val authRepository: AuthRepository
): UserDetailsService {

    @Transactional(readOnly = true)
    override fun loadUserByUsername(email: String): UserDetails {
        return authRepository.findByEmail(email)
            ?: throw CustomException(UserErrorCode.USER_NOT_FOUND)
    }
}