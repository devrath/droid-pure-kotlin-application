package com.iprayforgod.login_presentation.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.iprayforgod.login_domain.usecases.LoginModuleUseCases
import com.iprayforgod.login_domain.entities.inputs.RegistrationInput
import com.iprayforgod.login_presentation.R
import com.iprayforgod.login_presentation.states.registration.RegistrationUiState
import com.iprayforgod.login_presentation.states.registration.RegistrationViewEvent
import com.iprayforgod.core.modules.keys.KeysFeatureNames.FEATURE_LOGIN
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.core.platform.base.BaseViewModel
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.core.platform.functional.UseCaseResult
import com.iprayforgod.core.platform.ui.uiEvent.UiEvent
import com.iprayforgod.core.platform.ui.uiEvent.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Types of communication
 * **********
 * RegistrationViewEvent ---> Compose UI communicates with view model
 * viewState ---> We change the state so that the compose-ui attached in view is updated
 *
 */
@HiltViewModel
class RegistrationVm @Inject constructor(
    private var loginModuleUseCases: LoginModuleUseCases,
    private var log: LoggerRepository
) : BaseViewModel() {

    var viewState by mutableStateOf(RegistrationUiState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: RegistrationViewEvent) {
        when (event) {
            is RegistrationViewEvent.OnRegisterViewClick -> actionRegistration()
            is RegistrationViewEvent.OnViewChangedConfirmPassword -> {
                viewState = viewState.copy(confirmPwd = event.valueConfirmPwd)
            }
            is RegistrationViewEvent.OnViewChangedEmail -> {
                viewState = viewState.copy(email = event.valueEmail)
            }
            is RegistrationViewEvent.OnViewChangedFirstName -> {
                viewState = viewState.copy(firstName = event.valueFirstName)
            }
            is RegistrationViewEvent.OnViewChangedLastName -> {
                viewState = viewState.copy(lastName = event.valueLastName)
            }
            is RegistrationViewEvent.OnViewChangedPassword -> {
                viewState = viewState.copy(pwd = event.valuePwd)
            }
            is RegistrationViewEvent.OnViewLoaderVisibility -> {
                viewState = viewState.copy(isLoaderVisible = event.isVisible)
            }
        }
    }

    private fun actionRegistration() {
        log.d(FEATURE_LOGIN, "ACTION:->  Registration action functionality is invoked")
        viewModelScope.launch {
            val input = registrationInput()
            val registrationValidation = withContext(Dispatchers.Default) {
                validateFieldsForRegistration(input)
            }
            if (registrationValidation) {
                initiateRegistration(input)
            }
        }
    }

    /** ********************************** USE CASES **********************************************/
    /**
     * USE CASE: use case for email field validations
     */
    private suspend fun validateFieldsForRegistration(input: RegistrationInput): Boolean {
        log.d(FEATURE_LOGIN, "USE CASE:->  registration fields validations invoked")
        loginModuleUseCases.validateRegistration.invoke(input)
            .onSuccess {
                return if (it.successful) {
                    true
                } else {
                    useCaseErrorMessage(it.errorMessage)
                    false
                }
            }
            .onFailure {
                useCaseError(UseCaseResult.Error(Exception(it)))
                return false
            }
        return false
    }

    /**
     * ERROR HANDLING:
     * Displaying messages to the snack-bar
     */
    private suspend fun useCaseErrorMessage(result: UiText?) {
        result?.let { _uiEvent.send(UiEvent.ShowSnackbar(it)) }
    }

    /**
     * ERROR HANDLING:
     * For the Use cases
     */
    private suspend fun useCaseError(result: UseCaseResult.Error) {
        val uiEvent = UiText.DynamicString(result.exception.message.toString())
        _uiEvent.send(UiEvent.ShowSnackbar(uiEvent))
    }
    /** ********************************** USE CASES **********************************************/

    /** ********************************** API CALLS **********************************************/
    private fun initiateRegistration(input: RegistrationInput) {

        viewModelScope.launch {
            loginModuleUseCases.registerUseCase(input).collect { state ->
                when (state) {
                    is State.Success -> {
                        log.d(FEATURE_LOGIN, "REGISTRATION API SUCCESS")
                        viewState = viewState.copy(isLoaderVisible = false)
                        _uiEvent.send(UiEvent.Success)
                    }
                    is State.Loading -> {
                        log.d(FEATURE_LOGIN, "REGISTRATION API LOADING")
                        viewState = viewState.copy(isLoaderVisible = true)
                    }
                    is State.Failed -> {
                        log.d(FEATURE_LOGIN, "REGISTRATION API FAILED")
                        viewState = viewState.copy(isLoaderVisible = false)
                        useCaseErrorMessage(UiText.StringResource(R.string.str_registration_failed))
                    }
                }
            }
        }
    }
    /** ********************************** API CALLS **********************************************/

    private fun registrationInput(): RegistrationInput {
        return RegistrationInput(
            firstName = viewState.firstName, lastName = viewState.lastName,
            email = viewState.email, password = viewState.pwd, confirmPassword = viewState.confirmPwd
        )
    }
}
