package com.iprayforgod.login_domain.cases

import com.iprayforgod.login_domain.cases.forgotPassword.ValidateForgotPwdUseCase
import com.iprayforgod.login_domain.cases.forgotPassword.ForgotPwdUseCase
import com.iprayforgod.login_domain.cases.login.LoginUserUseCase
import com.iprayforgod.login_domain.cases.login.ValidateLoginEntriesUseCase
import com.iprayforgod.login_domain.cases.registration.RegisterUserUseCase
import com.iprayforgod.login_domain.cases.registration.ValidateRegistrationEntriesUseCase

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
