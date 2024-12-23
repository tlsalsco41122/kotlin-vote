package com.manchae.kotlin_vote.global.exception

import org.springframework.http.HttpStatus

interface CustomErrorCode {

    val status: HttpStatus //
    val message: String // 설명
}