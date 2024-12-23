package com.manchae.kotlin_vote.domain.auth.service

import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.JoinReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.LoginReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.RefreshReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.response.LoginRes
import com.manchae.kotlin_vote.domain.auth.presentation.dto.response.RefreshRes
import com.manchae.kotlin_vote.global.common.BaseResponse

interface AuthService {
    fun joinUser(joinReq: JoinReq): BaseResponse<Unit>
    fun loginUser(loginReq: LoginReq): BaseResponse<LoginRes>
    fun refreshToken(refreshReq: RefreshReq): BaseResponse<RefreshRes>
}
