package com.info.chattingserver.domain.chat.controller

import com.info.chattingserver.domain.chat.controller.dto.request.SendMessageRequest
import com.info.chattingserver.domain.chat.service.ChatService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/chat")
class ChatController(
    private val chatService: ChatService
) {

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    fun sendMessage(
        @RequestBody @Valid
        req: SendMessageRequest
    ) { chatService.send(req.text!!) }

}