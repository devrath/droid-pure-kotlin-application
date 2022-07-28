package com.droid.login_domain.usecases.cases

import com.droid.login_domain.usecases.cases.forgotPassword.ValidateForgotPwdUseCase
import com.droid.login_domain.usecases.cases.login.LoginUserUseCase
import com.droid.login_domain.usecases.cases.login.ValidateLoginEntriesUseCase
import com.droid.login_domain.usecases.cases.registration.RegisterUserUseCase
import com.droid.login_domain.usecases.cases.registration.ValidateRegistrationEntriesUseCase

data class LoginModuleUseCases(
    // --> ***************** VALIDATIONS *****************
    val validateLogin: ValidateLoginEntriesUseCase,
    val validateRegistration: ValidateRegistrationEntriesUseCase,
    val validateForgotPassword: ValidateForgotPwdUseCase,
    // --> ***************** VALIDATIONS *****************
    // --> ***************** REPO_CALL *******************
    val registerUseCase: RegisterUserUseCase,
    val loginUseCase: LoginUserUseCase
    // --> ***************** REPO_CALL *******************
)
