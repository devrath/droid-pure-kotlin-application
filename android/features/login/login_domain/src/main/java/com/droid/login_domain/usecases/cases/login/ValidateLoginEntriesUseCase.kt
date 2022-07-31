package com.droid.login_domain.usecases.cases.login

import android.util.Patterns
import com.droid.login_domain.R
import com.droid.login_domain.usecases.ValidationResult
import com.droid.login_domain.usecases.entities.inputs.LoginInput
import com.droid.login_domain.usecases.utils.UseCaseUtils
import com.iprayforgod.core.di.qualifiers.IoDispatcher
import com.iprayforgod.core.modules.keys.KeysFeatureNames
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.core.platform.ui.uiEvent.UiText
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * USE CASE: Validate all the validation scenarios of email field
 * ********
 * Condition-1: Email field should not be blank
 * Condition-2: Email should match certain pattern
 * Condition-3: Number of characters in the password must be greater than equal to 8
 * Condition-4: Password needs to contain minimum of one letter and one digit
 */
class ValidateLoginEntriesUseCase @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val log: LoggerRepository,
) {

    suspend operator fun invoke(
        input: LoginInput
    ): Result<ValidationResult> {
        try {
            val result = initiateValidation(input)
            return Result.success(result)
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
    }

    private fun initiateValidation(input: LoginInput): ValidationResult {

        if (input.email.isBlank()) {
            log.d(KeysFeatureNames.FEATURE_LOGIN, "VALIDATION:-> Email entered is blank")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_email_cant_be_blank)
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(input.email).matches()) {
            log.d(KeysFeatureNames.FEATURE_LOGIN, "VALIDATION:-> Not valid email format")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_email_is_not_valid)
            )
        }

        // -> Number of characters in the password must be greater than equal to 8
        if (input.password.length < 8) {
            log.d(KeysFeatureNames.FEATURE_LOGIN, "VALIDATION:-> Password length is not proper")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_pwd_no_of_chars)
            )
        }
        // -> Password needs to contain minimum of one letter and one digit
        val containsLettersAndDigits = UseCaseUtils.containsLettersAndDigits(input.password)
        if (!containsLettersAndDigits) {
            log.d(KeysFeatureNames.FEATURE_LOGIN, "VALIDATION:-> Password does not contain letter and digits")
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_msg_pwd_with_letter_and_digit)
            )
        }
        log.d(KeysFeatureNames.FEATURE_LOGIN, "VALIDATION:-> Email and Password validation successful")
        return ValidationResult(
            successful = true
        )
    }
}
