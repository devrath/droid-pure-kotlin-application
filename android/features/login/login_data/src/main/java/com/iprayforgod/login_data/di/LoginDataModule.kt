package com.iprayforgod.login_data.di

import com.iprayforgod.login_data.repository.LoginRepositoryImpl
import com.iprayforgod.login_data.service.firebase.forgotPwd.ForgotPwdService
import com.iprayforgod.login_data.service.firebase.login.LoginService
import com.iprayforgod.login_data.service.firebase.registration.RegistrationService
import com.iprayforgod.login_domain.usecases.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginDataModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        regServ: RegistrationService,
        loginServ: LoginService,
        forgotPwdService: ForgotPwdService
    ): LoginRepository {
        return LoginRepositoryImpl(
            registrationService = regServ,
            loginService = loginServ,
            forgotPwdService = forgotPwdService
        )
    }
}
