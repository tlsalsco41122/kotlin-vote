package com.manchae.kotlin_vote.domain.auth.service

import com.manchae.kotlin_vote.domain.auth.domain.AuthRepository
import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.JoinReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.LoginReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.RefreshReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.response.LoginRes
import com.manchae.kotlin_vote.domain.auth.presentation.dto.response.RefreshRes
import com.manchae.kotlin_vote.global.common.BaseResponse
import org.springframework.transaction.annotation.Transactional

class AuthServiceImpl(
    private val authRepository: AuthRepository
): AuthService {

    @Transactional
    override fun joinUser(joinReq: JoinReq): BaseResponse<Unit> {
        if(authRepository.existsByEmail(joinReq.email)) throw
    }

    @Transactional
    override fun loginUser(loginReq: LoginReq): BaseResponse<LoginRes> {

    }

    @Transactional
    override fun refreshToken(refreshReq: RefreshReq): BaseResponse<RefreshRes> {

    }
}