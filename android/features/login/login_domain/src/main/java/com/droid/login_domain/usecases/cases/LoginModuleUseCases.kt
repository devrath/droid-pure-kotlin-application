package com.droid.login_domain.usecases.cases

import com.droid.login_domain.usecases.cases.registration.ValidateRegistrationEntriesUseCase

data class LoginModuleUseCases(
    val validateRegistration: ValidateRegistrationEntriesUseCase,
)
