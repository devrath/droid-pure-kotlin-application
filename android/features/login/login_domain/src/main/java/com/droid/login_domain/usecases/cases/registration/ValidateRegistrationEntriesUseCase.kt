package com.droid.login_domain.usecases.cases.registration

import android.util.Patterns
import com.droid.login_domain.R
import com.droid.login_domain.usecases.ValidationResult
import com.droid.login_domain.usecases.entities.RegistrationInput
import com.droid.login_domain.usecases.states.RegistrationViewStates
import com.droid.login_domain.usecases.utils.UseCaseUtils.containsLettersAndDigits
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
 * Condition-1: First name should not be blank
 * Condition-2: Last name should not be blank
 * Condition-3: Email should not be blank
 * Condition-4: Email should match certain pattern
 * Condition-5: Password cannot be blank
 * Condition-6: Password and confirm password must contain a letter and number
 * Condition-6: Confirm password cannot be blank
 * Condition-7: Password and confirm password must match
 * ********
 */
open class ValidateRegistrationEntriesUseCase @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val log: LoggerRepository,
    ) : SuspendUseCase<RegistrationInput, UseCaseResult<RegistrationViewStates>>(dispatcher) {

    override suspend fun execute(input: RegistrationInput): UseCaseResult<RegistrationViewStates> =
        suspendCancellableCoroutine { coroutine ->
            CoroutineScope(dispatcher).launch {
                try {
                    val result = initiateEmailValidation(input)
                    coroutine.resume(UseCaseResult.Success(RegistrationViewStates.RegistrationValidationStatus(result)))
                } catch (ex: Exception) {
                    coroutine.resumeWithException(Exception(ex.message))
                }
            }
        }

    private fun initiateEmailValidation(input: RegistrationInput): ValidationResult {
        // --> Condition-1: First name should not be blank
        if(input.firstName.isBlank()) {
            log.d(FEATURE_LOGIN,"VALIDATION:-> First name entered is blank")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_first_name_cant_be_blank)
            )
        }
        // --> Condition-2: Last name should not be blank
        if(input.lastName.isBlank()) {
            log.d(FEATURE_LOGIN,"VALIDATION:-> Last name entered is blank")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_last_name_cant_be_blank)
            )
        }
        // --> Condition-3: Email should not be blank
        if(input.email.isBlank()) {
            log.d(FEATURE_LOGIN,"VALIDATION:-> Email entered is blank")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_email_cant_be_blank)
            )
        }
        // --> Condition-4: Email should match certain pattern
        if(!Patterns.EMAIL_ADDRESS.matcher(input.email).matches()) {
            log.d(FEATURE_LOGIN,"VALIDATION:-> Not valid email format")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_email_is_not_valid)
            )
        }
        // --> Condition-5: Password cannot be blank
        if(input.password.isBlank()) {
            log.d(FEATURE_LOGIN,"VALIDATION:-> Password entered is blank")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_password_cant_be_blank)
            )
        }
        // --> Condition-6: Password and confirm password must contain a letter and number
        val containsLettersAndDigits = containsLettersAndDigits(input.password)
        if(!containsLettersAndDigits) {
            log.d(FEATURE_LOGIN,"VALIDATION:-> Password does not contain letter and digits")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_pwd_with_letter_and_digit)
            )
        }
        // --> Condition-6: Confirm password cannot be blank
        if(input.confirmPassword.isBlank()) {
            log.d(FEATURE_LOGIN,"VALIDATION:-> Confirm password entered is blank")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_confirm_password_cant_be_blank)
            )
        }
        // --> Condition-7: Password and confirm password must match
        if(!input.password.equals(input.confirmPassword,ignoreCase = false)) {
            log.d(FEATURE_LOGIN,"VALIDATION:-> Password and Confirm password does not match")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_passwords_does_not_match)
            )
        }

        log.d(FEATURE_LOGIN,"VALIDATION:-> Email validation successful")
        return ValidationResult(
            successful = true
        )
    }

}