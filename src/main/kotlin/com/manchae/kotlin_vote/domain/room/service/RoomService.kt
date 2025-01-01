package com.manchae.kotlin_vote.domain.room.service

import com.manchae.kotlin_vote.domain.room.domain.RoomRepository
import com.manchae.kotlin_vote.domain.room.domain.mapper.RoomMapper
import com.manchae.kotlin_vote.domain.room.exception.RoomErrorCode
import com.manchae.kotlin_vote.domain.room.presentation.dto.request.RoomReq
import com.manchae.kotlin_vote.domain.room.presentation.dto.response.AllRoomRes
import com.manchae.kotlin_vote.domain.room.presentation.dto.response.RoomRes
import com.manchae.kotlin_vote.global.common.BaseResponse
import com.manchae.kotlin_vote.global.exception.CustomException
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class RoomService(
    private val roomRepository: RoomRepository,
    private val roomMapper: RoomMapper
) {

    fun createRoom(roomReq: RoomReq): BaseResponse<Unit>{

        if (roomReq.title.isBlank())
            throw CustomException(RoomErrorCode.ROOM_EMPTY_TITLE)
        if (roomReq.content.isBlank())
            throw CustomException(RoomErrorCode.ROOM_EMPTY_CONTENT)

        roomRepository.save(
            roomMapper.toEntity(roomReq)
        )

        return BaseResponse(
            message = "방 생성 성공"
        )
    }

    fun getAllRooms(): BaseResponse<List<AllRoomRes>>{

        val list = roomRepository.findAll().map {
            roomMapper.toAllRoomRes(it)
        }

        return BaseResponse(
            message = "모든 방 조회 성공",
            data = list
        )
    }

    fun getRoom(id: Long): BaseResponse<RoomRes>{

        val room = roomRepository.findById(id)
            .orElseThrow{ CustomException(RoomErrorCode.ROOM_NOT_FOUND) }

        return BaseResponse(
            message = "방 조회 성공",
            data = roomMapper.toRoomRes(room)
        )
    }

    fun deleteRoom(id: Long): BaseResponse<Unit>{
        val room = roomRepository.findById(id)
            .orElseThrow { CustomException(RoomErrorCode.ROOM_NOT_FOUND) }

        roomRepository.delete(room)

        return BaseResponse(
            message = "방 삭제 성공"
        )
    }
}