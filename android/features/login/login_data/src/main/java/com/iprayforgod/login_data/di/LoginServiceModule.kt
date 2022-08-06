package com.iprayforgod.login_data.di

import com.iprayforgod.core.domain.features.firebase.FirebaseAuthFeature
import com.iprayforgod.core.domain.features.firebase.FirebaseFirestoreFeature
import com.iprayforgod.core.domain.features.logger.LoggerFeature
import com.iprayforgod.login_data.service.firebase.forgotPwd.ForgotPwdServiceImpl
import com.iprayforgod.login_data.service.firebase.registration.RegistrationServiceImpl
import com.iprayforgod.login_domain.service.ForgotPwdService
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
    fun provideRegistrationService(
        firebaseAuthRepository: FirebaseAuthFeature,
        logService: LoggerFeature,
        firebaseFirestoreRepository: FirebaseFirestoreFeature
    ): RegistrationServiceImpl {
        return RegistrationServiceImpl(
            serviceFirebase = firebaseAuthRepository,
            log = logService,
            serviceFirestore = firebaseFirestoreRepository
        )
    }


    @Provides
    @Singleton
    fun provideForgotPwdService(
        authFeature: FirebaseAuthFeature,
        log: LoggerFeature
    ): ForgotPwdService {
        return ForgotPwdServiceImpl(authFeature,log)
    }

}
