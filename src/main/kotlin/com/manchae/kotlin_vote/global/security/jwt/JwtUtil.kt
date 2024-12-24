package com.manchae.kotlin_vote.global.security.jwt

import com.manchae.kotlin_vote.domain.auth.presentation.dto.User
import com.manchae.kotlin_vote.domain.auth.presentation.dto.response.LoginRes
import com.manchae.kotlin_vote.global.security.jwt.enums.TokenType
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Component
class JwtUtil(
    private val jwtProperties: JwtProperties,
) {

    fun createToken(user: User): LoginRes {
        val refreshToken = createRefreshToken(user)
        val accessToken = createAccessToken(user)

        return LoginRes("Bearer $accessToken", "Bearer $refreshToken")
    }

    private fun createRefreshToken(user: User): String {
        return Jwts.builder()
            .setHeaderParam(Header.JWT_TYPE, TokenType.REFRESH)
            .claim("email",user.email)
            .claim("role",user.role)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.refreshExp))
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .compact()
    }
    private fun createAccessToken(user: User): String {
        return Jwts.builder()
            .setHeaderParam(Header.JWT_TYPE, TokenType.ACCESS)
            .claim("email",user.email)
            .claim("role",user.role)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExp))
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .compact()
    }
}