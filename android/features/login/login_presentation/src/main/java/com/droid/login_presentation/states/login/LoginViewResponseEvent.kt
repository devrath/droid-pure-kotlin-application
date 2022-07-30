package com.droid.login_presentation.states.login

import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.platform.ui.uiEvent.UiText

sealed class LoginViewResponseEvent {
    object SaveUserSuccess : LoginViewResponseEvent()
    data class ShowSnackbar(val message: UiText) : LoginViewResponseEvent()
    data class LoginApiSuccess(val user: User) : LoginViewResponseEvent()
}
