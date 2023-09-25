package com.info.chattingserver.global.config.kafka

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration




@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig: WebSocketMessageBrokerConfigurer {

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws-chat")
            .setAllowedOriginPatterns("*").withSockJS()
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.run {
            this.enableSimpleBroker("/topic")
            this.setApplicationDestinationPrefixes("/app")
        }
    }

    override fun configureWebSocketTransport(registry: WebSocketTransportRegistration) {
        registry.run {
            this.setMessageSizeLimit(160 * 64 * 1024)
            this.setSendTimeLimit(100 * 10000)
            this.setSendBufferSizeLimit(3 * 512 * 1024)
        }
    }
}