package com.manchae.kotlin_vote.domain.room.domain.mapper

import com.manchae.kotlin_vote.domain.room.domain.entity.RoomEntity
import com.manchae.kotlin_vote.domain.room.presentation.dto.request.RoomReq
import com.manchae.kotlin_vote.domain.room.presentation.dto.response.AllRoomRes
import com.manchae.kotlin_vote.domain.room.presentation.dto.response.RoomRes
import org.springframework.stereotype.Component

@Component
class RoomMapper {

    fun toRoomRes(entity: RoomEntity): RoomRes {
        return RoomRes(
            title = entity.title,
            content = entity.content
        )
    }

    fun toAllRoomRes(entity: RoomEntity): AllRoomRes {
        return AllRoomRes(
            title = entity.title
        )
    }

    fun toEntity(roomReq: RoomReq): RoomEntity {
        return RoomEntity(
            title = roomReq.title,
            content = roomReq.content
        )
    }
}