package com.manchae.kotlin_vote.global.security.jwt

import com.manchae.kotlin_vote.domain.auth.presentation.dto.User
import com.manchae.kotlin_vote.domain.auth.presentation.dto.response.LoginRes
import com.manchae.kotlin_vote.domain.auth.presentation.dto.response.RefreshRes
import com.manchae.kotlin_vote.global.exception.CustomException
import com.manchae.kotlin_vote.global.security.jwt.enums.TokenType
import com.manchae.kotlin_vote.global.security.jwt.exception.JwtErrorCode
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtil(
    private val jwtProperties: JwtProperties,
) {

    private val secretKey : SecretKey = Keys.hmacShaKeyFor(
        jwtProperties.secretKey.toByteArray(StandardCharsets.UTF_8)
    )

    fun getUserEmail(token: String): String {
        val claims = getClaims(token)
        return claims.body["email"] as String
    }

    fun getToken(token: String): String {
        return token.removePrefix("Bearer ")
    }

    fun isWrongType(claims: Jws<Claims>, tokenType: TokenType): Boolean {
        val type = claims.header.get(Header.JWT_TYPE) as String
        return type != tokenType.name
    }

    fun getClaims(token: String): Jws<Claims> {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(getToken(token))
        } catch (e: JwtException) {
            throw CustomException(JwtErrorCode.ILLEGAL_TOKEN)
        }
    }

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
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }
    private fun createAccessToken(user: User): String {
        return Jwts.builder()
            .setHeaderParam(Header.JWT_TYPE, TokenType.ACCESS)
            .claim("email",user.email)
            .claim("role",user.role)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExp))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }


    fun refreshToken(user: User): RefreshRes { // 토큰 리프레시
        val newAccessToken = createAccessToken(user)

        return RefreshRes("Bearer $newAccessToken")
    }
}