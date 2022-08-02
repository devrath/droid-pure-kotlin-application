package com.iprayforgod.login_presentation.states.login

data class LoginUiState(
    val email: String = "",
    val pwd: String = "",
    val isLoaderVisible: Boolean = false
)
