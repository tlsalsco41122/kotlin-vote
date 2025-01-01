package com.manchae.kotlin_vote.domain.room.exception

import com.manchae.kotlin_vote.global.exception.CustomErrorCode
import org.springframework.http.HttpStatus

enum class RoomErrorCode(
    override val status: HttpStatus,
    override val message: String
): CustomErrorCode {

    ROOM_NOT_FOUND(HttpStatus.NOT_FOUND,"방을 찾을 수 없습니다"),
    ROOM_EMPTY_TITLE(HttpStatus.BAD_REQUEST, "방 제목은 반드시 입력해야 합니다."),
    ROOM_EMPTY_CONTENT(HttpStatus.BAD_REQUEST, "방 내용은 반드시 입력해야 합니다.")

}