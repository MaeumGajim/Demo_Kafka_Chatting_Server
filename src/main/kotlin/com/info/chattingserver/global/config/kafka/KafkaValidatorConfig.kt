package com.info.chattingserver.global.config.kafka

import com.info.chattingserver.domain.chat.entity.Message
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.KafkaListenerConfigurer
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

@Configuration
@EnableKafka
class KafkaValidatorConfig(
    private val validator: LocalValidatorFactoryBean,
    private val kafkaConsumerConfig: KafkaConsumerConfig
): KafkaListenerConfigurer {

    override fun configureKafkaListeners(registrar: KafkaListenerEndpointRegistrar) { registrar.setValidator(validator) }

    @Bean
    fun messageFactory() = ConcurrentKafkaListenerContainerFactory<String, Message>()
        .also {
            it.consumerFactory = DefaultKafkaConsumerFactory(
                kafkaConsumerConfig.consumerConfigs(),
                StringDeserializer(),
                JsonDeserializer(Message::class.java)
            )
            it.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL
        }

}