package com.info.chattingserver.domain.auth.service

import com.info.chattingserver.domain.auth.exception.PasswordNotMatchedException
import com.info.chattingserver.domain.auth.presentation.dto.request.SignInRequest
import com.info.chattingserver.domain.auth.presentation.dto.response.TokenResponse
import com.info.chattingserver.domain.user.entity.User
import com.info.chattingserver.domain.user.repository.UserRepository
import com.info.chattingserver.global.config.jwt.TokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider
): AuthService {

    @Transactional
    override fun signIn(req: SignInRequest): TokenResponse {

        val user = userRepository.findByAccountId(req.accountId!!)
            ?: userRepository.save(User(
                req.accountId,
                passwordEncoder.encode(req.password!!)
            ))

        if (!passwordEncoder.matches(req.password!!, user.password)) throw PasswordNotMatchedException

        return tokenProvider.receiveToken(user.accountId)
    }
}