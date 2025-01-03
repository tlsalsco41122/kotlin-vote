package com.manchae.kotlin_vote.domain.auth.service

import com.manchae.kotlin_vote.domain.auth.domain.AuthRepository
import com.manchae.kotlin_vote.domain.auth.domain.mapper.UserMapper
import com.manchae.kotlin_vote.domain.auth.exception.UserErrorCode
import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.JoinReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.LoginReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.RefreshReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.response.LoginRes
import com.manchae.kotlin_vote.domain.auth.presentation.dto.response.RefreshRes
import com.manchae.kotlin_vote.global.common.BaseResponse
import com.manchae.kotlin_vote.global.exception.CustomException
import com.manchae.kotlin_vote.global.security.jwt.JwtUtil
import com.manchae.kotlin_vote.global.security.jwt.enums.TokenType
import com.manchae.kotlin_vote.global.security.jwt.exception.JwtErrorCode
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceImpl(
    private val authRepository: AuthRepository,
    private val encoder: BCryptPasswordEncoder,
    private val jwtUtil: JwtUtil,
    private val userMapper: UserMapper
): AuthService {

    @Transactional
    override fun joinUser(joinReq: JoinReq): BaseResponse<Unit> {

        if(authRepository.existsByEmail(joinReq.email))
            throw CustomException(UserErrorCode.USER_ALREADY)

        authRepository.save(
            userMapper.toEntity(
                userMapper.toDomain(
                    joinReq, password = encoder.encode(joinReq.password)
                )
            )
        )

        return BaseResponse(
            message = "회원가입 성공"
        )
    }

    @Transactional
    override fun loginUser(loginReq: LoginReq): BaseResponse<LoginRes> {

        val user = authRepository.findByEmail(loginReq.email)
            ?: throw CustomException(UserErrorCode.USER_NOT_FOUND)

        if(!encoder.matches(loginReq.password, user.password))
            throw CustomException(UserErrorCode.PASSWORD_WRONG)

        return BaseResponse(
            message = "로그인 성공",
            data = jwtUtil.createToken(
                user = userMapper.toDomain(user)
            )
        )
    }

    @Transactional
    override fun refreshToken(refreshReq: RefreshReq): BaseResponse<RefreshRes> {
        val token = jwtUtil.getToken(refreshReq.refreshToken)
        val claims = jwtUtil.getClaims(token)

        if(jwtUtil.isWrongType(claims, TokenType.REFRESH))
            throw CustomException(JwtErrorCode.TOKEN_TYPE_ERROR)

        val user = authRepository.findByEmail(
            jwtUtil.getUserEmail(token)
        )

        return BaseResponse(
            message = "리프레시 성공",
            data = jwtUtil.refreshToken(user = userMapper.toDomain(user!!))
        )

    }
}