package com.droid.login_presentation.states

sealed class LoginViewStates {
    object InitialState : LoginViewStates()
    object NoConnectivity : LoginViewStates()
    data class ErrorState(val errorMessage: String) : LoginViewStates()
}

