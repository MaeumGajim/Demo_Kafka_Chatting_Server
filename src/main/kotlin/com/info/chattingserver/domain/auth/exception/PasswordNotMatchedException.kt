package com.info.chattingserver.domain.auth.exception

import com.info.chattingserver.global.config.error.data.ErrorCode
import com.info.chattingserver.global.config.error.exception.BusinessException

object PasswordNotMatchedException : BusinessException(ErrorCode.PASSWORD_NOT_MATCHED)
