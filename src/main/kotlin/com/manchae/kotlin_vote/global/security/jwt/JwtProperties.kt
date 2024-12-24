package com.manchae.kotlin_vote.global.security.jwt

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
data class JwtProperties(
    @Value("\${jwt.secretKey}") val secretKey: String,
    @Value("\${jwt.refreshExp}") val refreshExp: Long,
    @Value("\${jwt.accessExp}") val accessExp: Long
)
