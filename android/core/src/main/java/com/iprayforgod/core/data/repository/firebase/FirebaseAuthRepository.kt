package com.iprayforgod.core.data.repository.firebase

import com.google.firebase.auth.FirebaseAuth
import com.iprayforgod.core.domain.features.firebase.FirebaseAuthFeature

class FirebaseAuthRepository(
    private val authFeature: FirebaseAuthFeature
) {

    fun getFirebaseAuth(): FirebaseAuth {
        return authFeature.getFirebaseAuth()
    }
}
