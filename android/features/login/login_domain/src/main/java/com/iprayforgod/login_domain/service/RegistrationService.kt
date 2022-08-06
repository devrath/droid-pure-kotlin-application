package com.iprayforgod.login_domain.service

import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.login_domain.entities.inputs.RegistrationInput
import kotlinx.coroutines.flow.Flow

interface RegistrationService {
    fun registerUser(input: RegistrationInput): Flow<State<User>>
}