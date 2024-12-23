package com.manchae.kotlin_vote.domain.auth.presentation.dto.request

data class LoginReq (
    val email: String = "",
    val password: String = ""
)