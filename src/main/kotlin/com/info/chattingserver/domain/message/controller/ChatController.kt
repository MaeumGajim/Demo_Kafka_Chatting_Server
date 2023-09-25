package com.info.chattingserver.domain.message.controller

import com.info.chattingserver.domain.message.entity.Message
import mu.KLogger
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatController(
    private var kafkaTemplate: KafkaTemplate<String, Message>,
    private val logger: KLogger
) {

    @PostMapping("/chat/send")
    fun sendMessage(
        @RequestBody
        message: Message
    ) {
        logger.info{ "message : ${message.text}, auth : ${message.author}" }
        kafkaTemplate.send(KAFKA_TOPIC, message)
    }

    companion object {
        const val KAFKA_TOPIC: String = "new-kafka-chat"
    }

}