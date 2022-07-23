package com.droid.login_domain.usecases.cases.registration

import com.droid.login_domain.usecases.entities.inputs.RegistrationInput
import com.droid.login_domain.usecases.repository.LoginRepository

class RegisterUserUseCase(
    private val loginRepo: LoginRepository
) {

    suspend operator fun invoke(input: RegistrationInput) {
        loginRepo.registerUser(input)
    }

}