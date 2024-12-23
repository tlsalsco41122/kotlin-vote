package com.manchae.kotlin_vote.domain.auth.service

import com.manchae.kotlin_vote.domain.auth.domain.AuthRepository
import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.JoinReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.LoginReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.RefreshReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.response.LoginRes
import com.manchae.kotlin_vote.domain.auth.presentation.dto.response.RefreshRes
import com.manchae.kotlin_vote.global.common.BaseResponse
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.transaction.annotation.Transactional

class AuthServiceImpl(
    private val authRepository: AuthRepository,
    private val encoder: BCryptPasswordEncoder
): AuthService {

    @Transactional
    override fun joinUser(joinReq: JoinReq): BaseResponse<Unit> {

        if(authRepository.existsByEmail(joinReq.email))

        return BaseResponse(
            message = "회원가입 성공"
        )
    }

    @Transactional
    override fun loginUser(loginReq: LoginReq): BaseResponse<LoginRes> {

        val user = authRepository.findByEmail(loginReq.email)
            ?: throw UsernameNotFoundException("존재하지 않는 이메일입니다.")

        if(!encoder.matches(loginReq.password, user.password))
            throw UsernameNotFoundException("비밀번호가 일치하지 않습니다.")

        return BaseResponse(
            message = "로그인 성공"
        )
    }

    @Transactional
    override fun refreshToken(refreshReq: RefreshReq): BaseResponse<RefreshRes> {

    }
}