package com.iprayforgod.core.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.iprayforgod.core.domain.features.firebase.FirebaseAuthFeature
import com.iprayforgod.core.modules.firebase.implementation.FirebaseAuthImpl
import com.iprayforgod.core.modules.firebase.repository.FirebaseAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * How module works: Instance creation happens from the bottom to the top
 * This Module provides the instance of repository to be used
 * *****
 * This module is to create a instance of firebase-auth module
 */
@Module
@InstallIn(SingletonComponent::class)
object FirebaseAuthModule {

    /**
     * STEP-3: ---> <Final Step>
     * Provides a instance of FirebaseAuthFeature
     * *********************************************
     * We always inject a repository
     */
    @Provides
    @Singleton
    fun provideFirebaseAuthRepository(
        firebaseAuthFeature: FirebaseAuthFeature
    ) = FirebaseAuthRepository(authFeature = firebaseAuthFeature)

    /**
     *  STEP-2: --->
     * Provides a instance of Fire-Base auth implementation
     * *********************************************
     * We don't inject the instance of implementation directly - instead we provide it to the repository above
     */
    @Provides
    @Singleton
    fun provideFirebaseAuthFeature(firebaseAuth: FirebaseAuth): FirebaseAuthFeature {
        return FirebaseAuthImpl(firebaseAuth)
    }

    /**
     * STEP-1: --->
     * Provides a instance of Fire-Base auth
     * *********************************************
     * We don't inject the instance of Fire-Base auth - Instead we provide it to the implementation above
     */
    @Singleton
    @Provides
    fun provideFirebaseAuthInstance(): FirebaseAuth {
        return Firebase.auth
    }
}
