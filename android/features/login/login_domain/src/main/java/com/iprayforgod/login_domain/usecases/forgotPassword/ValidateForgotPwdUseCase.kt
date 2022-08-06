package com.iprayforgod.login_domain.usecases.forgotPassword

import android.util.Patterns
import com.iprayforgod.core.domain.features.logger.LoggerFeature
import com.iprayforgod.core.modules.keys.KeysFeatureNames
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.core.platform.ui.uiEvent.UiText
import com.iprayforgod.login_domain.R
import com.iprayforgod.login_domain.ValidationResult
import com.iprayforgod.login_domain.entities.inputs.ForgotPwdInput
import javax.inject.Inject

class ValidateForgotPwdUseCase @Inject constructor(
    private val log: LoggerFeature,
) {

    operator fun invoke(
        input: ForgotPwdInput
    ): Result<ValidationResult> {
        return try {
            val result = initiateValidation(input)
            Result.success(result)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    private fun initiateValidation(input: ForgotPwdInput): ValidationResult {
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

        log.d(KeysFeatureNames.FEATURE_LOGIN, "VALIDATION:-> Email validation successful")
        return ValidationResult(
            successful = true
        )
    }
}
