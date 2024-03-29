package com.iprayforgod.login_domain.usecases.registration

import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.login_domain.entities.inputs.RegistrationInput
import com.iprayforgod.login_domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class RegisterUserUseCase(
    private val loginRepo: LoginRepository
) {

    operator fun invoke(input: RegistrationInput): Flow<State<User>> {
        return loginRepo.registerUser(input)
    }
}
