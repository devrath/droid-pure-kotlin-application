package com.iprayforgod.onboarding_domain.usecases

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
