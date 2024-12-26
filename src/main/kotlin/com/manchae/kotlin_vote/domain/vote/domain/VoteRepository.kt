package com.manchae.kotlin_vote.domain.vote.domain

import com.manchae.kotlin_vote.domain.vote.domain.entity.VoteEntity
import org.springframework.data.jpa.repository.JpaRepository

interface VoteRepository: JpaRepository<VoteEntity, Long> {
}