package com.info.chattingserver.global.config.kafka.event

import com.info.chattingserver.domain.chat.entity.Message
import com.info.chattingserver.domain.chat.service.ChatServiceImpl.Companion.KAFKA_TOPIC
import mu.KLogger
import mu.KotlinLogging
import org.apache.tomcat.util.net.SocketEvent
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.listener.KafkaListenerErrorHandler
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
class MessageListener(
    private val template: SimpMessagingTemplate
) {

    private companion object {
        const val groupId = "0"
        val logger: KLogger = KotlinLogging.logger {  }
    }

    @KafkaListener(
        id="message_validate",
        topics = [KAFKA_TOPIC],
        groupId = groupId,
        containerFactory = "messageFactory"
    )
    fun consume(message: Message) {
        logger.info{ "[Kafka Consume] Message=${message.text} Author=${message.author} SendAt=${message.sendAt}" }
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