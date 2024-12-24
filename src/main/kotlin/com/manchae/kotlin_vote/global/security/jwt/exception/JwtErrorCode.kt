package com.manchae.kotlin_vote.global.security.jwt.exception

import com.manchae.kotlin_vote.global.exception.CustomErrorCode
import org.springframework.http.HttpStatus

enum class JwtErrorCode(
    override val status: HttpStatus,
    override val message: String
): CustomErrorCode{

    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰"),
    ILLEGAL_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 토큰"),
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "토큰이 존재하지 않음"),
    TOKEN_TYPE_ERROR(HttpStatus.BAD_REQUEST,"잘못된 타입"),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED,"지원되지 않는 토큰");
}