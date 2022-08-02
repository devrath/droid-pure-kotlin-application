package com.iprayforgod.login_presentation.states.forgotPassword

sealed class ForgotPwdViewEvent {
    object OnSubmitClick : ForgotPwdViewEvent()
    data class OnViewChangedEmail(val valueEmail: String) : ForgotPwdViewEvent()
    data class OnViewLoaderVisibility(val isVisible: Boolean) : ForgotPwdViewEvent()
}
