package com.droid.login_data.di

import com.droid.login_data.service.LoginService
import com.iprayforgod.core.modules.firebase.repository.FirebaseAuthRepository
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginServiceModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        firebaseAuthRepository: FirebaseAuthRepository,
        logService: LoggerRepository
    ): LoginService {
        return LoginService(
            service = firebaseAuthRepository, log = logService
        )
    }

}