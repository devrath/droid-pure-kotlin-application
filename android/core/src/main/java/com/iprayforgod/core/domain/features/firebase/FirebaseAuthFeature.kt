package com.iprayforgod.core.domain

import com.google.firebase.auth.FirebaseAuth

interface FirebaseAuthFeature {
    fun getFirebaseAuth() : FirebaseAuth
}