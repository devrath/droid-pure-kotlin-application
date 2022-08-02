package com.droid.login_domain.usecases.repository

import com.droid.login_domain.usecases.entities.inputs.ForgotPwdInput
import com.droid.login_domain.usecases.entities.inputs.LoginInput
import com.droid.login_domain.usecases.entities.inputs.RegistrationInput
import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.platform.functional.State
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun registerUser(input: RegistrationInput): Flow<State<User>>
    fun loginUser(input: LoginInput): Flow<State<User>>
    fun forgotPassword(input: ForgotPwdInput): Flow<State<Boolean>>
}
