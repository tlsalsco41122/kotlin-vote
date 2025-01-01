package com.manchae.kotlin_vote.global.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.manchae.kotlin_vote.global.common.BaseResponse
import com.manchae.kotlin_vote.global.security.jwt.exception.JwtErrorCode
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.SignatureException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtFilter(
    private val jwtUtils: JwtUtil,
    private val objectMapper: ObjectMapper
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token: String? = request.getHeader("Authorization")

        if (token.isNullOrEmpty()) {
            filterChain.doFilter(request, response)
            return
        }

        // "Bearer "로 시작하지 않으면 오류 처리
        if (!token.startsWith("Bearer ")) {
            setErrorResponse(response, JwtErrorCode.TOKEN_TYPE_ERROR)
            return
        }
        try {
            val claims = jwtUtils.getClaims(jwtUtils.getToken(token))
            SecurityContextHolder.getContext().authentication = jwtUtils.getAuthentication(token)
            filterChain.doFilter(request, response)
        } catch (e: ExpiredJwtException) {
            setErrorResponse(response, JwtErrorCode.EXPIRED_TOKEN)
        } catch (e: SignatureException) {
            setErrorResponse(response, JwtErrorCode.SIGNATURE_ERROR)
        } catch (e: UnsupportedJwtException) {
            setErrorResponse(response, JwtErrorCode.UNSUPPORTED_TOKEN)
        } catch (e: Exception) {
            setErrorResponse(response, JwtErrorCode.TOKEN_NOT_FOUND)
        }
    }

    private fun setErrorResponse(
        response: HttpServletResponse,
        errorCode: JwtErrorCode
    ) {
        response.status = errorCode.status.value()
        response.contentType = "application/json;charset=UTF-8"

        response.writer.write(
            objectMapper.writeValueAsString(
                BaseResponse<String>(
                    status = errorCode.status.value(),
                    state = errorCode.name,
                    message = errorCode.message
                )
            )
        )
    }
}