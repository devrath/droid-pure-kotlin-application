package com.iprayforgod.core.modules.firebase.implementation

import com.google.firebase.firestore.FirebaseFirestore
import com.iprayforgod.core.domain.FirebaseFirestoreFeature

class FirebaseFirestoreImpl (
    private val firebaseFirestore: FirebaseFirestore
) : FirebaseFirestoreFeature {

    override fun getFirebaseFirestore(): FirebaseFirestore { return firebaseFirestore }

}