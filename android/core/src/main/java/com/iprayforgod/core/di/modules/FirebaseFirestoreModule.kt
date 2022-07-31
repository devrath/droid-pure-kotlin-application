package com.iprayforgod.core.di.modules

import com.google.firebase.firestore.FirebaseFirestore
import com.iprayforgod.core.domain.features.firebase.FirebaseFirestoreFeature
import com.iprayforgod.core.modules.firebase.implementation.FirebaseFirestoreImpl
import com.iprayforgod.core.modules.firebase.repository.FirebaseFirestoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseFirestoreModule {

    /**
     * STEP-3: ---> <Final Step>
     * Provides a instance of FirebaseFirestoreFeature
     * *********************************************
     * We always inject a repository
     */
    @Provides
    @Singleton
    fun provideFirebaseAuthRepository(
        firebaseFirestoreFeature: FirebaseFirestoreFeature
    ) = FirebaseFirestoreRepository(fireStore = firebaseFirestoreFeature)

    /**
     *  STEP-2: --->
     * Provides a instance of Fire-Store implementation
     * *********************************************
     * We don't inject the instance of implementation directly - instead we provide it to the repository above
     */
    @Provides
    @Singleton
    fun provideFirebaseFirestoreFeature(firebaseFirestore: FirebaseFirestore): FirebaseFirestoreFeature {
        return FirebaseFirestoreImpl(firebaseFirestore)
    }

    /**
     * STEP-1: --->
     * Provides a instance of Fire-Store instance
     * *********************************************
     * We don't inject the instance of Fire-Store instance - Instead we provide it to the implementation above
     */
    @Singleton
    @Provides
    fun provideFirebaseFirestoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}
