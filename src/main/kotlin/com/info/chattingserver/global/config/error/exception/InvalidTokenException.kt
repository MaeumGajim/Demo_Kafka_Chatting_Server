package com.info.chattingserver.global.config.error.exception

import com.info.chattingserver.global.config.error.data.ErrorCode


object InvalidTokenException: BusinessException(ErrorCode.INVALID_TOKEN)
