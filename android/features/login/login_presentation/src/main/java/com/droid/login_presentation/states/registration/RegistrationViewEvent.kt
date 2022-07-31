package com.droid.login_presentation.states.registration

sealed class RegistrationViewEvent {
    object OnRegisterViewClick : RegistrationViewEvent()
    data class OnViewChangedFirstName(val valueFirstName: String) : RegistrationViewEvent()
    data class OnViewChangedLastName(val valueLastName: String) : RegistrationViewEvent()
    data class OnViewChangedEmail(val valueEmail: String) : RegistrationViewEvent()
    data class OnViewChangedPassword(val valuePwd: String) : RegistrationViewEvent()
    data class OnViewChangedConfirmPassword(val valueConfirmPwd: String) : RegistrationViewEvent()
    data class OnViewLoaderVisibility(val isVisible: Boolean) : RegistrationViewEvent()
}
