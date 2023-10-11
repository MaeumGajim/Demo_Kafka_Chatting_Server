package com.info.chattingserver.global.config.socket

import com.info.chattingserver.global.config.error.exception.InvalidTokenException
import org.springframework.http.HttpStatus
import org.springframework.messaging.Message
import org.springframework.messaging.MessageDeliveryException
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.MessageBuilder
import org.springframework.messaging.support.MessageHeaderAccessor
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler
import java.nio.charset.StandardCharsets


@Component
class StompExceptionHandler : StompSubProtocolErrorHandler() {

    companion object {
        private val EMPTY_PAYLOAD = ByteArray(0)
    }

    override fun handleClientMessageProcessingError(
        clientMessage: Message<ByteArray>?,
        ex: Throwable
    ): Message<ByteArray>? {

        val exception = if (ex is MessageDeliveryException) {
            ex.cause
        } else ex

        return if (exception is InvalidTokenException) {
            handleUnauthorizedException(clientMessage!!, exception)
        } else {
            super.handleClientMessageProcessingError(clientMessage, ex)
        }
    }

    private fun handleUnauthorizedException(
        clientMessage: Message<ByteArray>,
        ex: Throwable?
    ): Message<ByteArray> = prepareErrorMessage(clientMessage, ex!!.message, HttpStatus.UNAUTHORIZED.name)

    private fun prepareErrorMessage(
        clientMessage: Message<ByteArray>,
        message: String?, errorCode: String
    ): Message<ByteArray> {

        val accessor = StompHeaderAccessor.create(StompCommand.ERROR)

        accessor.run {
            this.message = errorCode
            this.setLeaveMutable(true)
        }

        setReceiptIdForClient(clientMessage, accessor)

        return MessageBuilder.createMessage(
            message?.toByteArray(StandardCharsets.UTF_8) ?: EMPTY_PAYLOAD,
            accessor.messageHeaders
        )
    }

    private fun setReceiptIdForClient(
        clientMessage: Message<ByteArray>?,
        accessor: StompHeaderAccessor
    ) {
        MessageHeaderAccessor.getAccessor(clientMessage ?: return, StompHeaderAccessor::class.java)?.let {
            accessor.receiptId = it.receipt
        }
    }

    override fun handleInternal(
        errorHeaderAccessor: StompHeaderAccessor,
        errorPayload: ByteArray,
        cause: Throwable?,
        clientHeaderAccessor: StompHeaderAccessor?
    ): Message<ByteArray> = MessageBuilder.createMessage(errorPayload, errorHeaderAccessor.messageHeaders)
}
