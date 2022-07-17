package com.droid.login_domain.usecases

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
