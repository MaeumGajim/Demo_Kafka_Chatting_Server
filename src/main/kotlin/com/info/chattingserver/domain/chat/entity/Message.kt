package com.info.chattingserver.domain.chat.entity

import com.vane.badwordfiltering.BadWordFiltering
import java.time.LocalDateTime
import java.util.*

data class Message(

    val author: UUID,

    var text: String,

    val sendAt: LocalDateTime = LocalDateTime.now()
) {
    fun filtering() {
        this.text = BadWordFiltering().change(this.text)
    }
}
