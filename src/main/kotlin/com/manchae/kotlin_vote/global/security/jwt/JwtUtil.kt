package com.manchae.kotlin_vote.global.security.jwt

import com.manchae.kotlin_vote.domain.auth.presentation.dto.User
import com.manchae.kotlin_vote.domain.auth.presentation.dto.response.LoginRes
import com.manchae.kotlin_vote.global.security.jwt.enums.TokenType
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

    fun getUserEmail(token: String): String{
        return
    }

//    public boolean isWrongType(final Jws<Claims> claims, final JwtType jwtType) {
//        return !(claims.getHeader().get(Header.JWT_TYPE).equals(jwtType.toString()));
//    }

//    fun isWrongType(claims: Jws<Claims>, token: String): boolean{
//        return !(claims.getHeader().get(Header.JWT_TYPE).equals(TokenType.toString()))
//    }

    fun createToken(user: User): LoginRes {
        val refreshToken = createRefreshToken(user)
        val accessToken = createAccessToken(user)

        return LoginRes("Bearer $accessToken", "Bearer $refreshToken")
    }

//    fun refreshToken(user: User): RefreshRes{ // 토큰 리프레시
//        val newAccessToken = createAccessToken(user)
//
//        return RefreshRes("Bearer $newAccessToken")
//    }

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
}