package com.manchae.kotlin_vote.domain.auth.exception

import com.manchae.kotlin_vote.global.exception.CustomErrorCode
import org.springframework.http.HttpStatus

enum class UserErrorCode(
    override val status: HttpStatus,
    override val message: String
): CustomErrorCode {

    USER_ALREADY(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "이메일을 찾을 수 없습니다."),
    PASSWORD_WRONG(HttpStatus.BAD_REQUEST, "비밀번호가 맞지 않습니다."),


}