package com.iprayforgod.core.domain.features.firebase

import com.google.firebase.auth.FirebaseAuth

interface FirebaseAuthFeature {
    fun getFirebaseAuth() : FirebaseAuth
}