package com.iprayforgod.login_domain.usecases

import com.iprayforgod.login_domain.usecases.forgotPassword.ForgotPwdUseCase
import com.iprayforgod.login_domain.usecases.forgotPassword.ValidateForgotPwdUseCase
import com.iprayforgod.login_domain.usecases.login.LoginUserUseCase
import com.iprayforgod.login_domain.usecases.login.ValidateLoginEntriesUseCase
import com.iprayforgod.login_domain.usecases.registration.RegisterUserUseCase
import com.iprayforgod.login_domain.usecases.registration.ValidateRegistrationEntriesUseCase

data class LoginModuleUseCases(
    // --> ***************** VALIDATIONS *****************
    val validateLogin: ValidateLoginEntriesUseCase,
    val validateRegistration: ValidateRegistrationEntriesUseCase,
    val validateForgotPassword: ValidateForgotPwdUseCase,
    // --> ***************** VALIDATIONS *****************
    // --> ***************** REPO_CALL *******************
    val registerUseCase: RegisterUserUseCase,
    val loginUseCase: LoginUserUseCase,
    val forgotPwdUseCase: ForgotPwdUseCase,
    // val saveCurrentUserUseCase: SaveCurrentUserUseCase,
    // --> ***************** REPO_CALL *******************
)
// PreferenceDatastore
