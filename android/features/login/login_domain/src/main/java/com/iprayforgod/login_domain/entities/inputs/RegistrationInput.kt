package com.iprayforgod.login_domain.entities.inputs

data class RegistrationInput(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
