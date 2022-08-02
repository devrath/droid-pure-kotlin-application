package com.iprayforgod.login_domain.usecases.cases.forgotPassword

import com.iprayforgod.login_domain.usecases.entities.inputs.ForgotPwdInput
import com.iprayforgod.login_domain.usecases.repository.LoginRepository
import com.iprayforgod.core.platform.functional.State
import kotlinx.coroutines.flow.Flow

class ForgotPwdUseCase(
    private val loginRepo: LoginRepository
) {

    operator fun invoke(input: ForgotPwdInput): Flow<State<Boolean>> {
        return loginRepo.forgotPassword(input)
    }
}