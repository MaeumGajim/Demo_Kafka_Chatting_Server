package com.info.chattingserver.domain.chat.controller.dto.request

import javax.validation.constraints.NotBlank

data class SendMessageRequest(

    @field:NotBlank(message = "null이 될 수 없습니다.")
    val text: String?
)
