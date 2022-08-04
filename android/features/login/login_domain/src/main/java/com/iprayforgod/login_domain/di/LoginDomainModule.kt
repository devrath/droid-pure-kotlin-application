package com.iprayforgod.login_domain.di

import com.iprayforgod.login_domain.usecases.LoginModuleUseCases
import com.iprayforgod.login_domain.usecases.forgotPassword.ValidateForgotPwdUseCase
import com.iprayforgod.login_domain.usecases.forgotPassword.ForgotPwdUseCase
import com.iprayforgod.login_domain.usecases.login.LoginUserUseCase
import com.iprayforgod.login_domain.usecases.login.ValidateLoginEntriesUseCase
import com.iprayforgod.login_domain.usecases.registration.RegisterUserUseCase
import com.iprayforgod.login_domain.usecases.registration.ValidateRegistrationEntriesUseCase
import com.iprayforgod.login_domain.repository.LoginRepository
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object LoginDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        loggerRepository: LoggerRepository,
        loginRepo: LoginRepository
    ): LoginModuleUseCases {
        return LoginModuleUseCases(
            validateRegistration = ValidateRegistrationEntriesUseCase(log = loggerRepository),
            validateLogin = ValidateLoginEntriesUseCase(log = loggerRepository),
            validateForgotPassword = ValidateForgotPwdUseCase(log = loggerRepository),
            registerUseCase = RegisterUserUseCase(loginRepo = loginRepo),
            loginUseCase = LoginUserUseCase(loginRepo = loginRepo),
            forgotPwdUseCase = ForgotPwdUseCase(loginRepo = loginRepo)
        )
    }
}
