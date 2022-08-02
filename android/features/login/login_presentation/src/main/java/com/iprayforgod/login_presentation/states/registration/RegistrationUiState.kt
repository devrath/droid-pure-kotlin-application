package com.iprayforgod.login_presentation.states.registration

data class RegistrationUiState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val pwd: String = "",
    val confirmPwd: String = "",
    val isLoaderVisible: Boolean = false
)
