package com.info.chattingserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoKafkaChattingServerApplication

fun main(args: Array<String>) {
    runApplication<DemoKafkaChattingServerApplication>(*args)
}
