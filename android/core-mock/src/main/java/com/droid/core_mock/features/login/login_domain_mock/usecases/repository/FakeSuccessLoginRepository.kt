package com.droid.core_mock.features.login.login_domain_mock.usecases.repository

import com.droid.core_mock.core.domain.models.UserMocks
import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.login_domain.entities.inputs.ForgotPwdInput
import com.iprayforgod.login_domain.entities.inputs.LoginInput
import com.iprayforgod.login_domain.entities.inputs.RegistrationInput
import com.iprayforgod.login_domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSuccessLoginRepository : LoginRepository {

    override fun registerUser(input: RegistrationInput): Flow<State<User>> {
        return flow { emit(State.success(UserMocks.validUser())) }
    }

    override fun loginUser(input: LoginInput): Flow<State<User>> {
        return flow { emit(State.success(UserMocks.validUser())) }
    }

    override fun forgotPassword(input: ForgotPwdInput): Flow<State<Boolean>> {
        return flow { emit(State.success(data = true)) }
    }

}