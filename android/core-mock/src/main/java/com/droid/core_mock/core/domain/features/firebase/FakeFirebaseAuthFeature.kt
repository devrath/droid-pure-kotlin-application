package com.droid.core_mock.core.domain.features.firebase
/*

import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_EMAIL
import com.google.firebase.auth.FirebaseAuth
import com.iprayforgod.core.domain.features.firebase.FirebaseAuthFeature
import okhttp3.mockwebserver.MockWebServer
import org.mockito.Mockito
import org.mockito.Mockito.`when`




class FakeFirebaseAuthFeature : FirebaseAuthFeature {

    private var mockWebServer: MockWebServer = MockWebServer()

    private lateinit var okHttpClient: FirebaseAuth

    var successfulScenario: Boolean = true

    override fun getFirebaseAuth(): FirebaseAuth {
        val mockFirebaseAuth = Mockito.mock(FirebaseAuth::class.java)
        if(successfulScenario){
            // Send the result as success
            `when`(
                mockFirebaseAuth.sendPasswordResetEmail(VALID_EMAIL).isSuccessful
            ).thenReturn(successfulScenario)
        }else{
            // Send the result as failure
            `when`(
                mockFirebaseAuth.sendPasswordResetEmail(VALID_EMAIL).isSuccessful
            ).thenReturn(successfulScenario)
        }
        return  mockFirebaseAuth
    }

}*/
