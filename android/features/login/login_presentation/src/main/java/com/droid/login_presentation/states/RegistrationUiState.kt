package com.droid.login_presentation.states

data class RegistrationUiState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val pwd: String = "",
    val confirmPwd: String = "",
    val isLoaderVisible: Boolean = false,
)
