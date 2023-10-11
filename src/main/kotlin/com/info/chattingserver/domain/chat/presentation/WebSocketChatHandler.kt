package com.info.chattingserver.domain.chat.presentation

import mu.KLogger
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class WebSocketChatHandler: TextWebSocketHandler() {

    private companion object {
        val logger: KLogger = KotlinLogging.logger {  }
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload: String = message.payload
        logger.info { payload }
        session.sendMessage(TextMessage("Welcome Sever"))
    }
}