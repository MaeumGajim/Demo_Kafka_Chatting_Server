package com.info.chattingserver.domain.user.exception

import com.info.chattingserver.global.config.error.data.ErrorCode
import com.info.chattingserver.global.config.error.exception.BusinessException

object UserNotFoundException : BusinessException(ErrorCode.USER_NOT_FOUND)
