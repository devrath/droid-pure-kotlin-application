package com.iprayforgod.login_data.service.firebase.login

import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.modules.firebase.endpoints.Constants
import com.iprayforgod.core.modules.firebase.repository.FirebaseAuthRepository
import com.iprayforgod.core.modules.firebase.repository.FirebaseFirestoreRepository
import com.iprayforgod.core.modules.keys.KeysFeatureNames
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.login_domain.entities.inputs.LoginInput
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginService @Inject constructor(
    private val serviceFirebase: FirebaseAuthRepository,
    private val serviceFirestore: FirebaseFirestoreRepository,
    private var log: LoggerRepository
) {

    companion object {
        const val USER_DATA_FIREBASE_IS_NULL = "user data is null in firebase"
        const val USER_DATA_FIRESTORE_IS_NULL = "user data is null in firestore"
    }

    fun loginUser(input: LoginInput): Flow<State<User>> {

        val resultDeferred = CompletableDeferred<State<User>>()

        try {
            val result = serviceFirebase.getFirebaseAuth()
                .signInWithEmailAndPassword(input.email, input.password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        it.result.user?.let {
                            user ->
                            getUserDetails(user.uid, resultDeferred)
                        } ?: run {
                            log.e(KeysFeatureNames.FEATURE_LOGIN, USER_DATA_FIREBASE_IS_NULL)
                            resultDeferred.complete(State.failed(USER_DATA_FIREBASE_IS_NULL))
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
                emit(State.loading())
                emit(resultDeferred.await())
            } catch (e: Exception) {
                log.e(KeysFeatureNames.FEATURE_LOGIN, e.stackTrace.toString())
                resultDeferred.completeExceptionally(e)
            }
        }
    }

    private fun getUserDetails(userId: String, resultDeferred: CompletableDeferred<State<User>>) {

        /*
         * Here we pass the collection name from which we wants the data.
         * ***
         * Remember we created this table(document) during the registration
         */
        serviceFirestore.getFirebaseFirestore().collection(Constants.USERS)
            // The document id to get the Fields of user.
            .document(userId).get()
            .addOnSuccessListener { document ->
                log.i(KeysFeatureNames.FEATURE_LOGIN, document.toString())
                /**
                 * Here we have received the document snapshot which is converted into the User Data model object.
                 * Once we convert into the model object we can store in database
                 * **
                 * Remember don't save the model here instead it has to be done at repository level
                 **/
                val user = document.toObject(User::class.java)

                document.toObject(User::class.java)?.let { userDetails ->
                    resultDeferred.complete(State.success(userDetails))
                } ?: run {
                    log.e(KeysFeatureNames.FEATURE_LOGIN, USER_DATA_FIRESTORE_IS_NULL)
                    resultDeferred.complete(State.failed(USER_DATA_FIRESTORE_IS_NULL))
                }
            }
            .addOnFailureListener { e ->
                // Hide the progress dialog if there is any error. And print the error in log.
                val errorMsg = e.message
                log.e(KeysFeatureNames.FEATURE_LOGIN, "Error while getting user details:-> $errorMsg")
                resultDeferred.completeExceptionally(e)
            }
    }
}
