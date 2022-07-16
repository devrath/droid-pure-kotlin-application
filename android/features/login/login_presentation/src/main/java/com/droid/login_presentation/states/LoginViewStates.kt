package com.droid.login_presentation.states

import com.iprayforgod.core.ui.uiEvent.UiText

sealed class LoginViewStates {
    object InitialState : LoginViewStates()
    object NoConnectivity : LoginViewStates()
    data class ErrorState(val errorMessage: UiText) : LoginViewStates()
}

