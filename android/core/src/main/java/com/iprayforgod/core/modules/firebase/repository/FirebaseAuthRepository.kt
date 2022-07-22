package com.iprayforgod.core.modules.firebase.repository

import com.google.firebase.auth.FirebaseAuth
import com.iprayforgod.core.domain.FirebaseAuthFeature

class FirebaseAuthRepository(
    private val authFeature : FirebaseAuthFeature
) {

    fun getFirebaseAuth(): FirebaseAuth {
        return authFeature.getFirebaseAuth()
    }

}