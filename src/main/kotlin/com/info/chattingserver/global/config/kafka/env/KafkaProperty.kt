package com.info.chattingserver.global.config.kafka.env

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("spring.kafka.producer")
@ConstructorBinding
data class KafkaProperty(
    val bootstrapServers: String
)
