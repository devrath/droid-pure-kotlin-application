package com.droid.core_mock.features.login.login_domain_mock.usecases.repository

import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.login_domain.entities.inputs.ForgotPwdInput
import com.iprayforgod.login_domain.entities.inputs.LoginInput
import com.iprayforgod.login_domain.entities.inputs.RegistrationInput
import com.iprayforgod.login_domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFailureMsgCheckLoginRepository : LoginRepository {

    companion object {
        const val FAILURE_MESSAGE_FOR_REGISTRATION = "Registration failure"
        const val FAILURE_MESSAGE_FOR_LOGIN = "Login failure"
        const val FAILURE_MESSAGE_FOR_FORGOT_PWD = "Registration failure"
    }

    override fun registerUser(input: RegistrationInput): Flow<State<User>> {
        return flow { emit(State.failed(FAILURE_MESSAGE_FOR_REGISTRATION)) }
    }

    override fun loginUser(input: LoginInput): Flow<State<User>> {
        return flow { emit(State.failed(FAILURE_MESSAGE_FOR_LOGIN)) }
    }

    override fun forgotPassword(input: ForgotPwdInput): Flow<State<Boolean>> {
        return flow { emit(State.failed(FAILURE_MESSAGE_FOR_FORGOT_PWD)) }
    }
}
