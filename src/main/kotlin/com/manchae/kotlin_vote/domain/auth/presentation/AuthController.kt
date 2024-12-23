package com.manchae.kotlin_vote.domain.auth.presentation

import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.JoinReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.LoginReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.RefreshReq
import com.manchae.kotlin_vote.domain.auth.presentation.dto.response.LoginRes
import com.manchae.kotlin_vote.domain.auth.presentation.dto.response.RefreshRes
import com.manchae.kotlin_vote.domain.auth.service.AuthService
import com.manchae.kotlin_vote.global.common.BaseResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/join")
    fun joinUser(@RequestBody joinReq: JoinReq): BaseResponse<Unit>{
        return authService.joinUser(joinReq)
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody loginReq: LoginReq): BaseResponse<LoginRes>{
        return authService.loginUser(loginReq)
    }

    @PostMapping("/refresh")
    fun refreshToken(@RequestBody refreshReq: RefreshReq): BaseResponse<RefreshRes>{
        return authService.refreshToken(refreshReq)
    }
}