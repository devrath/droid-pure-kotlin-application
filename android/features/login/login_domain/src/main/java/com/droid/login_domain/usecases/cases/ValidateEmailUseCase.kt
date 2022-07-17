package com.droid.login_domain.usecases.cases

import android.util.Patterns
import com.droid.login_domain.usecases.ValidationResult
import com.iprayforgod.core.di.qualifiers.IoDispatcher
import com.iprayforgod.core.functional.SuspendUseCase
import com.iprayforgod.core.functional.UseCaseResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

open class ValidateEmailUseCase @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : SuspendUseCase<String, UseCaseResult<Boolean>>(dispatcher) {

    override suspend fun execute(email: String): UseCaseResult<Boolean> =
        suspendCancellableCoroutine { coroutine ->
            CoroutineScope(dispatcher).launch {
                try {
                    val result = initiateEmailValidation(email)
                    if(result.successful){
                        coroutine.resume(UseCaseResult.Success(true))
                    }else{
                        coroutine.resumeWithException(Exception(result.errorMessage))
                    }
                } catch (ex: Exception) {
                    coroutine.resumeWithException(Exception(ex.message))
                }
            }
        }

    private fun initiateEmailValidation(email: String): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "That's not a valid email"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}