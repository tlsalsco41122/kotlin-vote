package com.manchae.kotlin_vote.domain.vote.presentation

import com.manchae.kotlin_vote.domain.vote.presentation.dto.request.VoteReq
import com.manchae.kotlin_vote.domain.vote.service.VoteService
import com.manchae.kotlin_vote.global.common.BaseResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/votes")
class VoteController(
    private val voteService: VoteService
) {

    @PostMapping("/{roomId}")
    fun createVote(@PathVariable(name = "roomId") roomId: Long, @RequestBody voteReq: VoteReq): BaseResponse<Unit>{
        return voteService.createVote(roomId, voteReq)
    }

    @GetMapping("/{roomId}/results")
    fun getResults(@PathVariable(name = "roomId") roomId: Long): BaseResponse<Map<String, Long>>{
        return voteService.getResults(roomId)
    }
}