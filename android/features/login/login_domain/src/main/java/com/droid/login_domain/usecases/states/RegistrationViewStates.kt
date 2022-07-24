package com.droid.login_domain.usecases.states

import com.droid.login_domain.usecases.ValidationResult
import com.iprayforgod.core.platform.ui.uiEvent.UiText

sealed class RegistrationViewStates {
    object InitialState : RegistrationViewStates()
    object NoConnectivity : RegistrationViewStates()
    data class RegistrationValidationStatus(val result: ValidationResult) : RegistrationViewStates()
    object RegistrationValidationSuccessful : RegistrationViewStates()
    data class ErrorState(val errorMessage: UiText) : RegistrationViewStates()
    data class Loading(val isLoading: Boolean) : RegistrationViewStates()
}
