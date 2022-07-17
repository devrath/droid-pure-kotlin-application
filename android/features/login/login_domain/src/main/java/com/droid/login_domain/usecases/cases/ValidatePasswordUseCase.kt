package com.droid.login_domain.usecases.cases

import com.droid.login_domain.usecases.ValidationResult
import com.droid.login_domain.usecases.states.LoginViewStates
import com.iprayforgod.core.di.qualifiers.IoDispatcher
import com.iprayforgod.core.functional.SuspendUseCase
import com.iprayforgod.core.functional.UseCaseResult
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * USE CASE: Validate all the validation scenarios of password field
 * ********
 * Condition-1: Number of characters in the password must be greater than equal to 8
 * Condition-2: Password needs to contain minimum of one letter and one digit
 */
open class ValidatePasswordUseCase @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : SuspendUseCase<String, UseCaseResult<Boolean>>(dispatcher) {

    override suspend fun execute(password: String): UseCaseResult<Boolean> =
        suspendCancellableCoroutine { coroutine ->
            CoroutineScope(dispatcher).launch {
                try {
                    val result = initiatePasswordValidation(password)
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

    private fun initiatePasswordValidation(password: String): ValidationResult {
        // -> Number of characters in the password must be greater than equal to 8
        if(password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to consist of at least 8 characters"
            )
        }
        // -> Password needs to contain minimum of one letter and one digit
        val containsLettersAndDigits = containsLettersAndDigits(password)
        if(!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to contain at least one letter and digit"
            )
        }
        return ValidationResult(successful = true)
    }

    private fun containsLettersAndDigits(password: String) = password.any { it.isDigit() } &&
            password.any { it.isLetter() }

}