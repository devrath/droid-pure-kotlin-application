package com.droid.login_domain.usecases.di

import com.droid.login_domain.usecases.cases.LoginModuleUseCases
import com.droid.login_domain.usecases.cases.login.ValidateEmailUseCase
import com.droid.login_domain.usecases.cases.login.ValidatePasswordUseCase
import com.droid.login_domain.usecases.cases.registration.LoginUserUseCase
import com.droid.login_domain.usecases.cases.registration.RegisterUserUseCase
import com.droid.login_domain.usecases.cases.registration.ValidateRegistrationEntriesUseCase
import com.droid.login_domain.usecases.repository.LoginRepository
import com.iprayforgod.core.di.qualifiers.IoDispatcher
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object LoginDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        loggerRepository: LoggerRepository,
        loginRepo: LoginRepository
    ): LoginModuleUseCases {
        return LoginModuleUseCases(
            validateRegistration = ValidateRegistrationEntriesUseCase(
                log = loggerRepository, dispatcher = dispatcher
            ),
            validateEmail = ValidateEmailUseCase(
                log = loggerRepository, dispatcher = dispatcher
            ),
            validatePassword = ValidatePasswordUseCase(
                log = loggerRepository, dispatcher = dispatcher
            ),
            registerUseCase = RegisterUserUseCase(
                loginRepo = loginRepo
            ),
            loginUseCase = LoginUserUseCase(
                loginRepo = loginRepo
            )
        )
    }


}