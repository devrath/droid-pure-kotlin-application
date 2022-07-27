package com.droid.login_domain.usecases.cases

import com.droid.login_domain.usecases.cases.login.ValidateEmailUseCase
import com.droid.login_domain.usecases.cases.login.ValidatePasswordUseCase
import com.droid.login_domain.usecases.cases.registration.LoginUserUseCase
import com.droid.login_domain.usecases.cases.registration.RegisterUserUseCase
import com.droid.login_domain.usecases.cases.registration.ValidateRegistrationEntriesUseCase

data class LoginModuleUseCases(
    // --> ***************** VALIDATIONS *****************
    val validateRegistration: ValidateRegistrationEntriesUseCase,
    val validateEmail: ValidateEmailUseCase,
    val validatePassword: ValidatePasswordUseCase,
    // --> ***************** VALIDATIONS *****************
    // --> ***************** REPO_CALL *******************
    val registerUseCase: RegisterUserUseCase,
    val loginUseCase: LoginUserUseCase
    // --> ***************** REPO_CALL *******************
)
