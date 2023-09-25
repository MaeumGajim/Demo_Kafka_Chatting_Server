package com.info.chattingserver.domain.auth.presentation.dto.request

import javax.validation.constraints.NotBlank

data class SignInRequest(

    @field:NotBlank(message = "null이 될 수 없습니다.")
    val accountId: String?,

    @field:NotBlank(message = "null이 될 수 없습니다.")
    val password: String?
)
