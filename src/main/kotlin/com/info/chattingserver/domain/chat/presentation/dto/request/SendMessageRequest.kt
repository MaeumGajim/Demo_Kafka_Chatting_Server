package com.info.chattingserver.domain.chat.presentation.dto.request

import javax.validation.constraints.NotBlank

data class SendMessageRequest(

    @field:NotBlank(message = "null이 될 수 없습니다.")
    val text: String?
)
