package com.manchae.kotlin_vote.domain.auth.presentation.dto.request

data class LoginReq (
    val username: String = "",
    val password: String = ""
)