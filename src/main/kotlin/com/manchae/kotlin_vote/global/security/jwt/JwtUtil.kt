package com.manchae.kotlin_vote.global.security.jwt

import com.manchae.kotlin_vote.domain.auth.presentation.dto.User
import com.manchae.kotlin_vote.domain.auth.presentation.dto.response.LoginRes
import com.manchae.kotlin_vote.domain.auth.presentation.dto.response.RefreshRes
import com.manchae.kotlin_vote.global.exception.CustomException
import com.manchae.kotlin_vote.global.security.auth.AuthDetailsService
import com.manchae.kotlin_vote.global.security.jwt.enums.TokenType
import com.manchae.kotlin_vote.global.security.jwt.exception.JwtErrorCode
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.SignatureException
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtil(
    private val jwtProperties: JwtProperties,
    private val authDetailsService: AuthDetailsService
) {

    private val secretKey : SecretKey = Keys.hmacShaKeyFor(
        jwtProperties.secretKey.toByteArray(StandardCharsets.UTF_8)
    )

    fun getUserEmail(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body["email"] as String
    }

    fun getToken(token: String): String {
        return token.removePrefix("Bearer ")
    }

    fun isWrongType(claims: Jws<Claims>, tokenType: TokenType): Boolean {
        val type = claims.header[Header.JWT_TYPE] as String // JWT 타입을 헤더에서 추출
        return type != tokenType.name
    }

    fun getClaims(token: String): Jws<Claims> {
        try {
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
        } catch (e: ExpiredJwtException) {
            throw CustomException(JwtErrorCode.EXPIRED_TOKEN)
        } catch (e: SignatureException) {
            throw CustomException(JwtErrorCode.SIGNATURE_ERROR)
        } catch (e: MalformedJwtException) {
            throw CustomException(JwtErrorCode.ILLEGAL_TOKEN)
        } catch (e: UnsupportedJwtException) {
            throw CustomException(JwtErrorCode.UNSUPPORTED_TOKEN)
        } catch (e: IllegalArgumentException) {
            throw CustomException(JwtErrorCode.TOKEN_NOT_FOUND)
        } catch (e: Exception) {
            throw CustomException(JwtErrorCode.TOKEN_TYPE_ERROR)
        }
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails: UserDetails = authDetailsService.loadUserByUsername(getUserEmail(getToken(token)))
        return UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
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