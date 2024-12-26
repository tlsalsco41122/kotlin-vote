package com.manchae.kotlin_vote.domain.room.domain

import com.manchae.kotlin_vote.domain.room.domain.entity.RoomEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RoomRepository: JpaRepository<RoomEntity, Long> {
}