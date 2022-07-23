package com.droid.login_domain.usecases.repository

import com.droid.login_domain.usecases.entities.User
import com.droid.login_domain.usecases.entities.inputs.RegistrationInput
import com.iprayforgod.core.platform.functional.State
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun registerUser(input: RegistrationInput):  Flow<State<User>>
}