package com.info.chattingserver.domain.chat.presentation

import com.info.chattingserver.domain.chat.entity.Message
import com.info.chattingserver.domain.chat.presentation.dto.request.SendMessageRequest
import com.info.chattingserver.domain.chat.service.ChatService
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@Validated
@RestController
class ChatController(
    private val chatService: ChatService
) {

    @MessageMapping("/send")
    @SendTo("/topic/group")
    fun broadcast(
        @Payload
        message: Message
    ) = message

    @PostMapping(consumes = ["application/json"], produces = ["application/json"])
    fun send(
        @RequestBody @Valid
        req: SendMessageRequest
    ) {
        chatService.send(req.text!!)
    }
}