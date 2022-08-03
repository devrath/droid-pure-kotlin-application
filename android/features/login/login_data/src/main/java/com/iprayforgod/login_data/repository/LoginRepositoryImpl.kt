package com.iprayforgod.login_data.repository

import com.iprayforgod.login_data.service.firebase.forgotPwd.ForgotPwdService
import com.iprayforgod.login_data.service.firebase.login.LoginService
import com.iprayforgod.login_data.service.firebase.registration.RegistrationService
import com.iprayforgod.login_domain.entities.inputs.ForgotPwdInput
import com.iprayforgod.login_domain.entities.inputs.LoginInput
import com.iprayforgod.login_domain.entities.inputs.RegistrationInput
import com.iprayforgod.login_domain.repository.LoginRepository
import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.platform.functional.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val registrationService: RegistrationService,
    private val loginService: LoginService,
    private val forgotPwdService: ForgotPwdService
) : LoginRepository {

    // --> REGISTER the user
    override fun registerUser(input: RegistrationInput): Flow<State<User>> {
        return registrationService.registerUser(input)
    }

    // --> LOGIN the user
    override fun loginUser(input: LoginInput): Flow<State<User>> {
        return loginService.loginUser(input)
    }

    // --> FORGOT-PWD for user
    override fun forgotPassword(input: ForgotPwdInput): Flow<State<Boolean>> {
        return forgotPwdService.forgotPwd(input)
    }
}
