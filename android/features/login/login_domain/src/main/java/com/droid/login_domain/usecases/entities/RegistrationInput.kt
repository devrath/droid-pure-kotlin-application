package com.droid.login_domain.usecases.entities

data class RegistrationInput(
    val firstName: String, val lastName: String,
    val email: String, val password: String,
    val confirmPassword: String
)
