package com.info.chattingserver.domain.auth.service

import com.info.chattingserver.domain.auth.presentation.dto.request.SignInRequest
import com.info.chattingserver.domain.auth.presentation.dto.response.TokenResponse

interface AuthService {

    fun signIn(req: SignInRequest): TokenResponse
}
