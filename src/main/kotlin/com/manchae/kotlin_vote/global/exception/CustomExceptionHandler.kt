package com.manchae.kotlin_vote.global.exception

import com.manchae.kotlin_vote.global.common.BaseResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(customException: CustomException): ResponseEntity<Any>{
        val response = BaseResponse<Unit>(
            status = customException.customErrorCode.status.value(),
            message = customException.customErrorCode.message
        )

        return ResponseEntity(response, customException.customErrorCode.status)
    }
}