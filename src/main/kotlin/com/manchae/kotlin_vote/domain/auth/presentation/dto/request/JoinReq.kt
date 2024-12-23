package com.manchae.kotlin_vote.domain.auth.presentation.dto.request

data class JoinReq(
    val email: String = "",
    val name: String = "",
    val password: String = ""
)