package com.iprayforgod.core.domain

import com.google.firebase.firestore.FirebaseFirestore

interface FirebaseFirestoreFeature {
    fun getFirebaseFirestore() : FirebaseFirestore
}