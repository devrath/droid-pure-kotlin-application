package com.iprayforgod.core.modules.firebase.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.iprayforgod.core.domain.FirebaseFirestoreFeature

class FirebaseFirestoreRepository(
    private val fireStore : FirebaseFirestoreFeature
)  {

    fun getFirebaseFirestore(): FirebaseFirestore {
        return fireStore.getFirebaseFirestore()
    }

}