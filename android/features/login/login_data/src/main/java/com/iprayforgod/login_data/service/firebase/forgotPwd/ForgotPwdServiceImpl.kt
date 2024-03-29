package com.iprayforgod.login_data.service.firebase.forgotPwd

import com.iprayforgod.core.domain.features.firebase.FirebaseAuthFeature
import com.iprayforgod.core.domain.features.logger.LoggerFeature
import com.iprayforgod.core.data.implementation.logger.utilities.KeysFeatureNames
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.login_domain.entities.inputs.ForgotPwdInput
import com.iprayforgod.login_domain.service.ForgotPwdService
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ForgotPwdServiceImpl @Inject constructor(
    private val service: FirebaseAuthFeature,
    private var log: LoggerFeature
) : ForgotPwdService {

    override fun forgotPwd(input: ForgotPwdInput): Flow<State<Boolean>> {

        val resultDeferred = CompletableDeferred<State<Boolean>>()

        try {
            val result = service.getFirebaseAuth()
                .sendPasswordResetEmail(input.email)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        resultDeferred.complete(State.success(true))
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
}
