package com.manchae.kotlin_vote.domain.vote.service

import com.manchae.kotlin_vote.domain.room.domain.RoomRepository
import com.manchae.kotlin_vote.domain.room.exception.RoomErrorCode
import com.manchae.kotlin_vote.domain.vote.domain.VoteRepository
import com.manchae.kotlin_vote.domain.vote.domain.mapper.VoteMapper
import com.manchae.kotlin_vote.domain.vote.presentation.dto.request.VoteReq
import com.manchae.kotlin_vote.global.common.BaseResponse
import com.manchae.kotlin_vote.global.exception.CustomException
import org.springframework.stereotype.Service

@Service
class VoteService (
    private val voteRepository: VoteRepository,
    private val roomRepository: RoomRepository,
    private val voteMapper: VoteMapper
    ){

    fun createVote(roomId: Long, voteReq: VoteReq): BaseResponse<Unit>{
        val room = roomRepository.findById(roomId)
            .orElseThrow{ CustomException(RoomErrorCode.ROOM_NOT_FOUND) }

        voteRepository.save(
            voteMapper.toEntity(voteReq, room)
        )

        return BaseResponse(
            message = "투표 생성 성공"
        )
    }

    fun getResults(roomId: Long): BaseResponse<Map<String, Long>>{

        roomRepository.findById(roomId)
            .orElseThrow { CustomException(RoomErrorCode.ROOM_NOT_FOUND) }

        val yesVotes = voteRepository.countByRoomIdAndChoice(roomId, true)
        val noVotes = voteRepository.countByRoomIdAndChoice(roomId, false)

        // 결과를 Map 형태로 반환
        val results = mapOf(
            "yes" to yesVotes,
            "no" to noVotes
        )

        return BaseResponse(
            message = "투표 결과 조회 성공",
            data = results
        )
    }

}