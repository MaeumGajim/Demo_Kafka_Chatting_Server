package com.info.chattingserver.global.config.kafka

import com.info.chattingserver.domain.chat.entity.Message
import com.info.chattingserver.global.config.kafka.env.KafkaProperty
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import java.io.Serializable


@Configuration
@EnableKafka
class KafkaConsumerConfig(
    private val kafkaProperty: KafkaProperty
) {
    @Bean
    fun consumerConfigs(): Map<String, Serializable> =
        mapOf(
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperty.bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to "0",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java
        )


    @Bean
    fun containerFactory(): ConcurrentKafkaListenerContainerFactory<String, Message> =
        ConcurrentKafkaListenerContainerFactory<String, Message>().run {
            consumerFactory = consumerFactory()
            return@run this
        }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Message> = DefaultKafkaConsumerFactory(consumerConfigs())
}