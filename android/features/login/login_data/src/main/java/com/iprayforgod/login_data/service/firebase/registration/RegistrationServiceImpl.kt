package com.iprayforgod.login_data.service.firebase.registration

import com.google.firebase.firestore.SetOptions
import com.iprayforgod.core.domain.features.firebase.FirebaseAuthFeature
import com.iprayforgod.core.domain.features.firebase.FirebaseFirestoreFeature
import com.iprayforgod.core.domain.features.logger.LoggerFeature
import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.data.implementation.firebase.utilities.endpoints.Constants
import com.iprayforgod.core.data.implementation.logger.utilities.KeysFeatureNames.FEATURE_LOGIN
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.login_domain.entities.inputs.RegistrationInput
import com.iprayforgod.login_domain.service.RegistrationService
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegistrationServiceImpl @Inject constructor(
    private val serviceFirebase: FirebaseAuthFeature,
    private val serviceFirestore: FirebaseFirestoreFeature,
    private var log: LoggerFeature
) : RegistrationService {

    /**
     * Here we make and entry for a user in the authentication module of firebase
     */
    override fun registerUser(
        input: RegistrationInput
    ): Flow<State<User>> {

        val resultDeferred = CompletableDeferred<State<User>>()

        try {
            val result = serviceFirebase.getFirebaseAuth()
                .createUserWithEmailAndPassword(input.email, input.password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        it.result.user?.let { firebaseUser ->
                            val user = User(
                                firebaseUser.uid, input.firstName, input.lastName, input.email
                            )
                            registerUser(user, resultDeferred)
                        }
                    } else {
                        resultDeferred.complete(State.failed(it.exception?.message.toString()))
                    }
                }
        } catch (ex: Exception) {
            resultDeferred.completeExceptionally(ex)
        }

        return flow {
            try {
                // Start the loader
                emit(State.loading())
                // Start the API calls
                emit(resultDeferred.await())
            } catch (e: Exception) {
                log.e(FEATURE_LOGIN, e.stackTrace.toString())
                resultDeferred.completeExceptionally(e)
            }
        }
    }

    /**
     * For each entry from the authentication module, we create the data in the fire-store
     */
    private fun registerUser(user: User, result: CompletableDeferred<State<User>>) {
        // The "users" is collection name. If the collection is already created then it will not create the same one again.
        serviceFirestore.getFirebaseFirestore().collection(Constants.USERS)
            // Document ID for users fields. Here the document it is the User ID.
            .document(user.id)
            // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge later on instead of replacing the fields.
            .set(user, SetOptions.merge())
            // On Success
            .addOnSuccessListener {
                /**
                 * Here the new user registered is automatically signed-in so we just sign-out the user from firebase
                 * and later in the flow once the confirmation is received we send the user to the login screen in presentation layer
                 */
                serviceFirebase.getFirebaseAuth().signOut()
                // Send success response
                result.complete(State.success(user))
            }
            // On Failure
            .addOnFailureListener { exception ->
                result.complete(State.failed(exception.message.toString()))
            }
    }
}
