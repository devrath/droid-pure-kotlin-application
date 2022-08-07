package com.iprayforgod.login_domain.usecases.registration

import android.util.Patterns
import com.iprayforgod.core.domain.features.logger.LoggerFeature
import com.iprayforgod.core.data.implementation.logger.utilities.KeysFeatureNames
import com.iprayforgod.core.platform.ui.uiEvent.UiText
import com.iprayforgod.login_domain.R
import com.iprayforgod.login_domain.ValidationResult
import com.iprayforgod.login_domain.entities.inputs.RegistrationInput
import com.iprayforgod.login_domain.utils.UseCaseUtils
import javax.inject.Inject

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
class ValidateRegistrationEntriesUseCase @Inject constructor(
    private val log: LoggerFeature,
) {

    operator fun invoke(
        input: RegistrationInput
    ): Result<ValidationResult> {
        return try {
            val result = initiateEmailValidation(input)
            Result.success(result)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    private fun initiateEmailValidation(input: RegistrationInput): ValidationResult {
        // --> Condition-1: First name should not be blank
        if (input.firstName.isBlank()) {
            log.d(KeysFeatureNames.FEATURE_LOGIN, "VALIDATION:-> First name entered is blank")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_first_name_cant_be_blank)
            )
        }
        // --> Condition-2: Last name should not be blank
        if (input.lastName.isBlank()) {
            log.d(KeysFeatureNames.FEATURE_LOGIN, "VALIDATION:-> Last name entered is blank")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_last_name_cant_be_blank)
            )
        }
        // --> Condition-3: Email should not be blank
        if (input.email.isBlank()) {
            log.d(KeysFeatureNames.FEATURE_LOGIN, "VALIDATION:-> Email entered is blank")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_email_cant_be_blank)
            )
        }
        // --> Condition-4: Email should match certain pattern
        if (!Patterns.EMAIL_ADDRESS.matcher(input.email).matches()) {
            log.d(KeysFeatureNames.FEATURE_LOGIN, "VALIDATION:-> Not valid email format")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_email_is_not_valid)
            )
        }
        // --> Condition-5: Password cannot be blank
        if (input.password.isBlank()) {
            log.d(KeysFeatureNames.FEATURE_LOGIN, "VALIDATION:-> Password entered is blank")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_password_cant_be_blank)
            )
        }
        // --> Condition-6: Password and confirm password must contain a letter and number
        val containsLettersAndDigits = UseCaseUtils.containsLettersAndDigits(input.password)
        if (!containsLettersAndDigits) {
            log.d(KeysFeatureNames.FEATURE_LOGIN, "VALIDATION:-> Password does not contain letter and digits")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_pwd_with_letter_and_digit)
            )
        }
        // --> Condition-6: Confirm password cannot be blank
        if (input.confirmPassword.isBlank()) {
            log.d(KeysFeatureNames.FEATURE_LOGIN, "VALIDATION:-> Confirm password entered is blank")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_confirm_password_cant_be_blank)
            )
        }
        // --> Condition-7: Password and confirm password must match
        if (!input.password.equals(input.confirmPassword, ignoreCase = false)) {
            log.d(KeysFeatureNames.FEATURE_LOGIN, "VALIDATION:-> Password and Confirm password does not match")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_passwords_does_not_match)
            )
        }

        log.d(KeysFeatureNames.FEATURE_LOGIN, "VALIDATION:-> Email validation successful")
        return ValidationResult(
            successful = true
        )
    }
}
