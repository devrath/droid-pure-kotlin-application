package com.droid.login_domain.usecases.cases

import com.droid.login_domain.usecases.cases.login.ValidateEmailUseCase
import com.droid.login_domain.usecases.cases.login.ValidatePasswordUseCase
import com.droid.login_domain.usecases.cases.registration.ValidateRegistrationEntriesUseCase

data class LoginModuleUseCases(
    val validateRegistration: ValidateRegistrationEntriesUseCase,
    val validateEmail: ValidateEmailUseCase,
    val validatePassword: ValidatePasswordUseCase,
)
