package com.manchae.kotlin_vote.domain.auth.presentation.dto

import com.manchae.kotlin_vote.domain.auth.domain.enums.UserType

data class User(
    val email: String = "",
    val name: String = "",
    val password: String = "",
    val role: UserType = UserType.ROLE_USER
)
