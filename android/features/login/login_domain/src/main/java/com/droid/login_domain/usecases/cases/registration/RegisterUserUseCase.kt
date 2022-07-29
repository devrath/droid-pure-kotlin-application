package com.droid.login_domain.usecases.cases.registration

import com.droid.login_domain.usecases.entities.inputs.RegistrationInput
import com.droid.login_domain.usecases.repository.LoginRepository
import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.platform.functional.State
import kotlinx.coroutines.flow.Flow

class RegisterUserUseCase(
    private val loginRepo: LoginRepository
) {

    operator fun invoke(input: RegistrationInput): Flow<State<User>> {
        return loginRepo.registerUser(input)
    }

}