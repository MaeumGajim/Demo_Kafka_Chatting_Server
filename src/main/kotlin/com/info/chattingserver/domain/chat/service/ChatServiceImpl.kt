package com.info.chattingserver.domain.chat.service

import com.info.chattingserver.domain.chat.entity.Message
import com.info.chattingserver.global.common.facade.UserFacade
import mu.KLogger
import mu.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ChatServiceImpl(
    private val kafkaTemplate: KafkaTemplate<String, Message>,
    private val userFacade: UserFacade
): ChatService {

    companion object {
        const val KAFKA_TOPIC: String = "chat"
        private val logger: KLogger = KotlinLogging.logger {  }
    }

    override fun send(text: String) {

        val message = Message(
            userFacade.getCurrentUser().id!!,
            text
        )

        kafkaTemplate.send(KAFKA_TOPIC, message)

        logger.info{ "[Kafka Send] Auth=${message.author} Chat=${message.text}" }
    }
}