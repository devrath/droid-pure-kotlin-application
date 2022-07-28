package com.droid.login_presentation.states.login

sealed class LoginViewEvent {
    object OnLoginViewClick: LoginViewEvent()
    data class OnViewChangedEmail(val valueEmail: String) : LoginViewEvent()
    data class OnViewChangedPassword(val valuePwd: String) : LoginViewEvent()
    data class OnViewLoaderVisibility(val isVisible: Boolean) : LoginViewEvent()
}
