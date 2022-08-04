package com.iprayforgod.login_domain.usecases.login

import com.iprayforgod.login_domain.entities.inputs.LoginInput
import com.iprayforgod.login_domain.repository.LoginRepository
import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.platform.functional.State
import kotlinx.coroutines.flow.Flow

class LoginUserUseCase(
    private val loginRepo: LoginRepository
) {

    operator fun invoke(input: LoginInput): Flow<State<User>> {
        return loginRepo.loginUser(input)
    }
}
