package com.info.chattingserver.global.config.security.principal

import com.info.chattingserver.domain.user.exception.UserNotFoundException
import com.info.chattingserver.domain.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AuthDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(accountId: String): UserDetails =
        AuthDetails(userRepository.findByAccountId(accountId) ?: throw UserNotFoundException)
}