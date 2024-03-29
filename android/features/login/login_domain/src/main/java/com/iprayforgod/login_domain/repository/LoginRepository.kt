package com.iprayforgod.login_domain.repository

import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.login_domain.entities.inputs.ForgotPwdInput
import com.iprayforgod.login_domain.entities.inputs.LoginInput
import com.iprayforgod.login_domain.entities.inputs.RegistrationInput
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun registerUser(input: RegistrationInput): Flow<State<User>>
    fun loginUser(input: LoginInput): Flow<State<User>>
    fun forgotPassword(input: ForgotPwdInput): Flow<State<Boolean>>
}
