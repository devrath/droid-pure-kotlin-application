package com.droid.login_domain.usecases.states

import com.droid.login_domain.usecases.ValidationResult
import com.iprayforgod.core.platform.ui.uiEvent.UiText

sealed class LoginViewStates {
    object InitialState : LoginViewStates()
    object NoConnectivity : LoginViewStates()
    data class Loading(val isLoading: Boolean) : LoginViewStates()
    data class LoginStatus(val isUserLoggedIn: Boolean) : LoginViewStates()
    data class EmailValidationStatus(val result: ValidationResult) : LoginViewStates()
    data class PasswordValidationStatus(val result: ValidationResult) : LoginViewStates()
    object LoginValidationSuccessful : LoginViewStates()
    data class ErrorState(val errorMessage: UiText) : LoginViewStates()
}

