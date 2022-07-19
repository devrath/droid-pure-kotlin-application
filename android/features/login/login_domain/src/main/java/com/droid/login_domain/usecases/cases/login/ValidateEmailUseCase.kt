package com.droid.login_domain.usecases.cases.login

import android.util.Patterns
import com.droid.login_domain.R
import com.droid.login_domain.usecases.ValidationResult
import com.droid.login_domain.usecases.states.LoginViewStates
import com.iprayforgod.core.di.qualifiers.IoDispatcher
import com.iprayforgod.core.modules.keys.KeysFeatureNames.FEATURE_LOGIN
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.core.platform.functional.SuspendUseCase
import com.iprayforgod.core.platform.functional.UseCaseResult
import com.iprayforgod.core.platform.ui.uiEvent.UiText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * USE CASE: Validate all the validation scenarios of email field
 * ********
 * Condition-1: Email field should not be blank
 * Condition-2: Email should match certain pattern
 */
open class ValidateEmailUseCase @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val log: LoggerRepository,
    ) : SuspendUseCase<String, UseCaseResult<LoginViewStates>>(dispatcher) {

    override suspend fun execute(email: String): UseCaseResult<LoginViewStates> =
        suspendCancellableCoroutine { coroutine ->
            CoroutineScope(dispatcher).launch {
                try {
                    log.d(FEATURE_LOGIN,"VALIDATION:-> Email check invoked")
                    val result = initiateEmailValidation(email)
                    coroutine.resume(UseCaseResult.Success(LoginViewStates.EmailValidationStatus(result)))
                } catch (ex: Exception) {
                    coroutine.resumeWithException(Exception(ex.message))
                }
            }
        }

    private fun initiateEmailValidation(email: String): ValidationResult {
        if(email.isBlank()) {
            log.d(FEATURE_LOGIN,"VALIDATION:-> Email entered is blank")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_email_cant_be_blank)
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            log.d(FEATURE_LOGIN,"VALIDATION:-> Not valid email format")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_email_is_not_valid)
            )
        }
        log.d(FEATURE_LOGIN,"VALIDATION:-> Email validation successful")
        return ValidationResult(
            successful = true
        )
    }

}