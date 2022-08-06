package com.iprayforgod.login_data.di

import com.iprayforgod.core.domain.features.firebase.FirebaseAuthFeature
import com.iprayforgod.core.domain.features.firebase.FirebaseFirestoreFeature
import com.iprayforgod.core.domain.features.logger.LoggerFeature
import com.iprayforgod.core.modules.firebase.repository.FirebaseAuthRepository
import com.iprayforgod.core.modules.firebase.repository.FirebaseFirestoreRepository
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.login_data.service.firebase.registration.RegistrationService
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
    ): RegistrationService {
        return RegistrationService(
            serviceFirebase = firebaseAuthRepository,
            log = logService,
            serviceFirestore = firebaseFirestoreRepository
        )
    }
}
