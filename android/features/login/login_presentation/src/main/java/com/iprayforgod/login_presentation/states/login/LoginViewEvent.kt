package com.iprayforgod.login_presentation.states.login

import com.iprayforgod.core.domain.models.User

sealed class LoginViewEvent {
    object OnLoginViewClick : LoginViewEvent()
    data class LoginSaveUserToPreference(val user: User) : LoginViewEvent()
    data class OnViewChangedEmail(val valueEmail: String) : LoginViewEvent()
    data class OnViewChangedPassword(val valuePwd: String) : LoginViewEvent()
    data class OnViewLoaderVisibility(val isVisible: Boolean) : LoginViewEvent()
}
