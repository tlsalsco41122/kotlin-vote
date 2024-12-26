package com.manchae.kotlin_vote.domain.room.service

import com.manchae.kotlin_vote.domain.room.domain.RoomRepository
import com.manchae.kotlin_vote.domain.room.presentation.dto.request.RoomReq
import com.manchae.kotlin_vote.domain.room.presentation.dto.response.AllRoomRes
import com.manchae.kotlin_vote.domain.room.presentation.dto.response.RoomRes
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RoomService(
    private val roomRepository: RoomRepository
) {

    fun createRoom(roomReq: RoomReq){
        roomRepository.save(roomReq)
    }

    fun getAllRooms(): List<AllRoomRes>{
        return roomRepository.findAll()
    }

    fun getRoom(id: Long): RoomRes{
        return roomRepository.findById(id)
    }

    fun deleteRoom(id: Long){
        return roomRepository.findById(id)
    }
}