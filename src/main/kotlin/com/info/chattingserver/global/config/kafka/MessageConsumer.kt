package com.info.chattingserver.global.config.kafka

import com.info.chattingserver.domain.chat.entity.Message
import com.info.chattingserver.domain.chat.service.ChatServiceImpl.Companion.KAFKA_TOPIC
import mu.KLogger
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.listener.KafkaListenerErrorHandler
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
class MessageConsumer(
    private val template: SimpMessagingTemplate,
    private val logger: KLogger
) {

    companion object {
        private const val groupId = "chatting"
    }

    @KafkaListener(id="message_validate", topics = [KAFKA_TOPIC], groupId = groupId, containerFactory = "messageFactory")
    fun consume(message: Message) {
        logger.info{ "Kafka Consume Message=${message.text} Author=${message.author} SendAt=${message.sendAt}" }
        template.convertAndSend("/topic/group", message)
    }

    @Bean
    fun validationErrorHandler(): KafkaListenerErrorHandler {
        return KafkaListenerErrorHandler { message, exception ->
            exception.printStackTrace()
            message
        }
    }

}