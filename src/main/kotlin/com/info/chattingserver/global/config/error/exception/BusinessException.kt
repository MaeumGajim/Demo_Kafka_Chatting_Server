package com.info.chattingserver.global.config.error.exception

import com.info.chattingserver.global.config.error.data.ErrorCode


open class BusinessException(val errorCode: ErrorCode): RuntimeException(errorCode.message)