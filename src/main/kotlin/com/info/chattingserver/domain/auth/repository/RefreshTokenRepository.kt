package com.info.chattingserver.domain.auth.repository

import com.info.chattingserver.domain.auth.entity.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository: CrudRepository<RefreshToken, String> {

    fun findByAccountId(accountId: String): RefreshToken?
}