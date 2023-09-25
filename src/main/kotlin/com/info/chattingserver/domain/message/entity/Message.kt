package com.info.chattingserver.domain.message.entity

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.util.UUID

data class Message(

    @JsonProperty("author")
    val author: UUID? = UUID.randomUUID(),

    @JsonProperty("text")
    val text: String?,

    @JsonProperty("created_at")
    val createdAt: LocalDateTime? = LocalDateTime.now()
)
