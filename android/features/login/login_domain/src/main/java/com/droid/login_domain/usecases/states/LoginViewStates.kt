package com.droid.login_domain.usecases.states

import com.iprayforgod.core.ui.uiEvent.UiText

sealed class LoginViewStates {
    object InitialState : LoginViewStates()
    object NoConnectivity : LoginViewStates()
    object LoginValidationSuccessful : LoginViewStates()
    data class ErrorState(val errorMessage: UiText) : LoginViewStates()
}

