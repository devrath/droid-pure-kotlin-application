package com.droid.core_mock.core.modules.firebase.repository

import com.iprayforgod.core.domain.features.firebase.FirebaseAuthFeature
import com.iprayforgod.core.modules.firebase.repository.FirebaseAuthRepository

class FakeFirebaseAuthRepository(authFeature: FirebaseAuthFeture) : FirebaseAuthRepository(
    authFeature
) {

}