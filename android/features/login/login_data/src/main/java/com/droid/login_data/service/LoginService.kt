package com.droid.login_data.service

import com.droid.login_domain.usecases.entities.User
import com.iprayforgod.core.modules.firebase.repository.FirebaseAuthRepository
import com.iprayforgod.core.platform.functional.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginService @Inject constructor(
    private val service : FirebaseAuthRepository
) {

    fun registerUser(
        firstName: String, lastName: String,
        email: String, password: String
    ) = flow<State<User>>{
        val result = service.getFirebaseAuth().createUserWithEmailAndPassword(email, password).result
        result.user?.let { firebaseUser ->
            val user = User(firebaseUser.uid, firstName, lastName, email)
            emit(State.success(user))
        }
    }.catch {
        // If exception is thrown, emit failed state along with message.
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)


}