package com.info.chattingserver.global.config.error.exception

import com.info.chattingserver.global.config.error.data.ErrorCode


object ExpiredTokenException: BusinessException(ErrorCode.EXPIRED_TOKEN)
