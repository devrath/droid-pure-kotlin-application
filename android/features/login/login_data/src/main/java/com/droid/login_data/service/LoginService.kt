package com.droid.login_data.service

import com.droid.login_domain.usecases.entities.User
import com.droid.login_domain.usecases.entities.inputs.RegistrationInput
import com.iprayforgod.core.modules.firebase.repository.FirebaseAuthRepository
import com.iprayforgod.core.modules.keys.KeysFeatureNames.FEATURE_LOGIN
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.core.platform.functional.State
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginService @Inject constructor(
    private val service: FirebaseAuthRepository,
    private var  log: LoggerRepository
) {

    companion object {
        const val RESULT_FAILURE = "firebase returned failure for the register user request"
    }

    fun registerUser(
        input: RegistrationInput
    ): Flow<State<User>> {

        val resultDeferred = CompletableDeferred<State<User>>()

        val result = service.getFirebaseAuth()
            .createUserWithEmailAndPassword(input.email, input.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    it.result.user?.let { firebaseUser ->
                        val user = User(
                            firebaseUser.uid, input.firstName, input.lastName, input.email
                        )
                        resultDeferred.complete(State.success(user))
                    }
                } else {
                    resultDeferred.complete(State.failed(RESULT_FAILURE))
                }
            }

        return flow {
            try {
                emit(resultDeferred.await())
            } catch (e: Exception) {
                log.e(FEATURE_LOGIN, e.stackTrace.toString())
            }
        }
    }

}