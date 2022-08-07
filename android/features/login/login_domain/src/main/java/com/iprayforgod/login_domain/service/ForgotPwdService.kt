package com.iprayforgod.login_domain.service

import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.login_domain.entities.inputs.ForgotPwdInput
import kotlinx.coroutines.flow.Flow

interface ForgotPwdService {
    fun forgotPwd(input: ForgotPwdInput): Flow<State<Boolean>>
}