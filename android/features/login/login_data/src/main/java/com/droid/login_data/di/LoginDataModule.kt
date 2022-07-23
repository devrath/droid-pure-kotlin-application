package com.droid.login_data.di

import com.droid.login_data.repository.LoginRepositoryImpl
import com.droid.login_data.service.LoginService
import com.droid.login_domain.usecases.repository.LoginRepository
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
        loginServ : LoginService
    ): LoginRepository {
        return LoginRepositoryImpl(
            loginService = loginServ
        )
    }


}