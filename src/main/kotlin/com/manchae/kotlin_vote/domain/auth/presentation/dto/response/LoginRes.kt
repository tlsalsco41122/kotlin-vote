package com.manchae.kotlin_vote.domain.auth.presentation.dto.response

data class LoginRes(
    val accessToken: String = "",
    val refreshToken: String = ""
)
