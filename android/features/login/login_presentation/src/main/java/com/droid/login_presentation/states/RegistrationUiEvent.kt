package com.droid.login_presentation.states

import com.iprayforgod.core.platform.ui.uiEvent.UiText

sealed class RegistrationUiEvent {
    object Success : RegistrationUiEvent()
    data class LoaderState(val isLoading: Boolean) : RegistrationUiEvent()
    data class ShowSnackBar(val message: UiText) : RegistrationUiEvent()
}
