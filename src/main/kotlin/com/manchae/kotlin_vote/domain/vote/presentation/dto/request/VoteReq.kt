package com.manchae.kotlin_vote.domain.vote.presentation.dto.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
// 이것들을 추가하니 에러가 해결됨...;;;;

/**
 * @JsonCreator: JSON 을 객체로 변환할 때 사용할 생성자를 지정합니다.
 * @JsonProperty("choice"): JSON 의 choice 필드를 VoteReq 클래스의 choice 프로퍼티에 매핑합니다.
 */

data class VoteReq @JsonCreator constructor(
    @JsonProperty("choice") val choice: Boolean
)
