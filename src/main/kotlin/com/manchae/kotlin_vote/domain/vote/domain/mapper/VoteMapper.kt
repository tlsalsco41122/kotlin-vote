package com.manchae.kotlin_vote.domain.vote.domain.mapper

import com.manchae.kotlin_vote.domain.room.domain.entity.RoomEntity
import com.manchae.kotlin_vote.domain.vote.domain.entity.VoteEntity
import com.manchae.kotlin_vote.domain.vote.presentation.dto.request.VoteReq
import org.springframework.stereotype.Component

@Component
class VoteMapper {

    fun toEntity(voteReq: VoteReq, room: RoomEntity): VoteEntity {
        return VoteEntity(
            choice = voteReq.choice,
            room = room
        )
    }
}