package com.manchae.kotlin_vote.global.exception

class CustomException(
    val customErrorCode: CustomErrorCode
): RuntimeException() {
}