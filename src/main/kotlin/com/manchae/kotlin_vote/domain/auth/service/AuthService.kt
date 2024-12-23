package com.manchae.kotlin_vote.domain.auth.service

import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.JoinReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.LoginReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.RefreshReq

interface AuthService {
    fun joinUser(joinReq: JoinReq)
    fun loginUser(loginReq: LoginReq)
    fun refreshToken(refreshReq: RefreshReq)
}
