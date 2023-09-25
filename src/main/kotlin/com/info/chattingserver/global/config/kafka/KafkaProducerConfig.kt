package com.info.chattingserver.global.config.kafka

import com.info.chattingserver.domain.message.entity.Message
import com.info.chattingserver.global.config.kafka.env.KafkaProperty
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonSerializer
import java.io.Serializable

@EnableKafka
@Configuration
class KafkaProducerConfig(
    private val kafkaProperty: KafkaProperty
) {

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Message> = KafkaTemplate(DefaultKafkaProducerFactory(producerConfigs()))

    @Bean
    fun producerConfigs(): Map<String, Serializable> =
        mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperty.bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
        )
}