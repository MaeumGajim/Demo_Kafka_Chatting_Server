package com.info.chattingserver.domain.auth.presentation

import com.info.chattingserver.domain.auth.presentation.dto.request.SignInRequest
import com.info.chattingserver.domain.auth.presentation.dto.response.TokenResponse
import com.info.chattingserver.domain.auth.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun signIn(
        @RequestBody @Valid
        req: SignInRequest
    ): TokenResponse = authService.signIn(req)
}