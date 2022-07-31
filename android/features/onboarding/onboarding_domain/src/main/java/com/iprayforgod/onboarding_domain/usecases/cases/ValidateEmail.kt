package com.iprayforgod.onboarding_domain.usecases.cases

import android.util.Patterns
import com.iprayforgod.onboarding_domain.usecases.ValidationResult

/**
 * USE CASE: Validate all the validation scenarios of password field
 * ********
 * Condition-1: Email field should not be blank
 * Condition-2: Certain patterns of the email is matched
 */
class ValidateEmail {

    fun execute(email: String): ValidationResult {
        // -> Email field should not be blank
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }
        // -> Certain patterns of the email is matched
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "That's not a valid email"
            )
        }
        return ValidationResult(successful = true)
    }
}
