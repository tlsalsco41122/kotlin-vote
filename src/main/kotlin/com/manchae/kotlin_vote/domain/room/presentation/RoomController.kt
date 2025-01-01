package com.manchae.kotlin_vote.domain.room.presentation

import com.manchae.kotlin_vote.domain.room.presentation.dto.request.RoomReq
import com.manchae.kotlin_vote.domain.room.presentation.dto.response.AllRoomRes
import com.manchae.kotlin_vote.domain.room.presentation.dto.response.RoomRes
import com.manchae.kotlin_vote.domain.room.service.RoomService
import com.manchae.kotlin_vote.global.common.BaseResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/room")
class RoomController(
    private val roomService: RoomService
) {

    @PostMapping
    fun createRoom(@RequestBody roomReq: RoomReq): BaseResponse<Unit>{
        return roomService.createRoom(roomReq)
    }

    @GetMapping
    fun getAllRooms(): BaseResponse<List<AllRoomRes>>{
        return roomService.getAllRooms()
    }

    @GetMapping("/{id}")
    fun getRoom(@PathVariable(name = "id") id: Long): BaseResponse<RoomRes>{
        return roomService.getRoom(id)
    }

    @DeleteMapping("/{id}")
    fun deleteRoom(@PathVariable(name = "id") id: Long): BaseResponse<Unit>{
        return roomService.deleteRoom(id)
    }

}