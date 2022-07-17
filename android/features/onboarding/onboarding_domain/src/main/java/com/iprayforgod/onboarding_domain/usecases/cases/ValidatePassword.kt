package com.iprayforgod.onboarding_domain.usecases.cases

import com.iprayforgod.onboarding_domain.usecases.ValidationResult

/**
 * USE CASE: Validate all the validation scenarios of password field
 * ********
 * Condition-1: Number of characters in the password must be greater than equal to 8
 * Condition-2: Password needs to contain minimum of one letter and one digit
 */
class ValidatePassword {

    fun execute(password: String): ValidationResult {
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