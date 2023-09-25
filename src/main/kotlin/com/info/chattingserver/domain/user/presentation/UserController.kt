package com.info.chattingserver.domain.user.presentation

import com.info.chattingserver.global.config.kafka.env.KafkaProperty
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val kafkaProperty: KafkaProperty
) {

    @GetMapping("/test")
    fun test() {
        println(kafkaProperty.bootstrapServers)
    }
}