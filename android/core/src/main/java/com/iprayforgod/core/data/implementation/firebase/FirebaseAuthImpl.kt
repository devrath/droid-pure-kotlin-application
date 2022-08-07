package com.iprayforgod.core.data.implementation.firebase

import com.google.firebase.auth.FirebaseAuth
import com.iprayforgod.core.domain.features.firebase.FirebaseAuthFeature

class FirebaseAuthImpl(
    private val firebaseAuth: FirebaseAuth
) : FirebaseAuthFeature {

    override fun getFirebaseAuth(): FirebaseAuth { return firebaseAuth }
}
