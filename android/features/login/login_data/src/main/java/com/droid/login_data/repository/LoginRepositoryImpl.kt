package com.droid.login_data.repository

import com.droid.login_data.service.LoginService
import com.droid.login_domain.usecases.entities.User
import com.droid.login_domain.usecases.entities.inputs.RegistrationInput
import com.droid.login_domain.usecases.repository.LoginRepository
import com.iprayforgod.core.platform.functional.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginService : LoginService
) : LoginRepository {
    override fun  registerUser(input: RegistrationInput): Flow<State<User>> {
        return loginService.registerUser(input)
    }
}