package com.info.chattingserver.domain.user.repository

import com.info.chattingserver.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository: JpaRepository<User, UUID?> {

    fun findByAccountId(accountId: String): User?
}