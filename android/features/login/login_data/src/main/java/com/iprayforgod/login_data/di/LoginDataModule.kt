package com.iprayforgod.login_data.di

import com.iprayforgod.login_data.repository.LoginRepositoryImpl
import com.iprayforgod.login_data.service.firebase.forgotPwd.ForgotPwdServiceImpl
import com.iprayforgod.login_data.service.firebase.login.LoginServiceImpl
import com.iprayforgod.login_data.service.firebase.registration.RegistrationServiceImpl
import com.iprayforgod.login_domain.repository.LoginRepository
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
        regServ: RegistrationServiceImpl,
        loginServ: LoginServiceImpl,
        forgotPwdServiceImpl: ForgotPwdServiceImpl
    ): LoginRepository {
        return LoginRepositoryImpl(
            registrationService = regServ,
            loginService = loginServ,
            forgotPwdService = forgotPwdServiceImpl
        )
    }
}
