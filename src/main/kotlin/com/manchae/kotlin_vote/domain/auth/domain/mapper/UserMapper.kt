package com.manchae.kotlin_vote.domain.auth.domain.mapper

import com.manchae.kotlin_vote.domain.auth.domain.entity.UserEntity
import com.manchae.kotlin_vote.domain.auth.presentation.dto.User
import com.manchae.kotlin_vote.domain.auth.presentation.dto.request.JoinReq
import org.springframework.stereotype.Component

@Component
class UserMapper() {

    fun toDomain(entity: UserEntity): User {
        return User(
            email = entity.email,
            name = entity.name,
            password = entity.password
        )
    }

    fun toEntity(domain: User): UserEntity {
        return UserEntity(
            email = domain.email,
            name = domain.name,
            password = domain.password
        )
    }

    fun toDomain(joinReq: JoinReq, password: String): User {
        return User(
            email = joinReq.email,
            name = joinReq.name,
            password = password
        )
    }

}