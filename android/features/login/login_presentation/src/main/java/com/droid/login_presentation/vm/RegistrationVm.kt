package com.droid.login_presentation.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.droid.login_domain.usecases.cases.LoginModuleUseCases
import com.droid.login_domain.usecases.entities.inputs.RegistrationInput
import com.droid.login_domain.usecases.states.RegistrationViewStates
import com.droid.login_presentation.states.RegistrationUiEvent
import com.droid.login_presentation.states.RegistrationUiState
import com.droid.login_presentation.states.RegistrationViewEvent
import com.iprayforgod.core.modules.keys.KeysFeatureNames.FEATURE_LOGIN
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.core.platform.base.BaseViewModel
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.core.platform.functional.UseCaseResult
import com.iprayforgod.core.platform.functional.data
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
    private var  loginModuleUseCases: LoginModuleUseCases,
    private var  log: LoggerRepository
) : BaseViewModel() {

    var viewState by mutableStateOf(RegistrationUiState())
        private set

    private val _registrationUiEvent = Channel<RegistrationUiEvent>()
    val registrationUiEvent = _registrationUiEvent.receiveAsFlow()


    fun onEvent(event: RegistrationViewEvent) {
        when(event) {
            is RegistrationViewEvent.OnRegisterViewClick ->  actionRegistration()
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
        log.d(FEATURE_LOGIN,"ACTION:->  Registration action functionality is invoked")
        viewModelScope.launch {
            val input = registrationInput()
            val registrationValidation = withContext(Dispatchers.Default) {
                validateFieldsForRegistration(input)
            }
            if(registrationValidation){
                initiateRegistration(input)
            }
        }
    }

    /** ********************************** USE CASES **********************************************/
    /**
     * USE CASE: use case for email field validations
     */
    private suspend fun validateFieldsForRegistration(input: RegistrationInput): Boolean {
        log.d(FEATURE_LOGIN,"USE CASE:->  registration fields validations invoked")

        when (val result = loginModuleUseCases.validateRegistration.invoke(input)) {
            is UseCaseResult.Success -> {
                val registrationValidationResult = result.value.data as RegistrationViewStates.RegistrationValidationStatus
                if(registrationValidationResult.result.successful){
                    return true
                }else{
                    useCaseErrorMessage(registrationValidationResult.result.errorMessage)
                    return false
                }
            }
            is UseCaseResult.Error -> {
                useCaseError(result)
                return false
            }
        }
        return false
    }

    /**
     * ERROR HANDLING:
     * Displaying messages to the snack-bar
     */
    private suspend fun useCaseErrorMessage(result: UiText?) {
        result?.let { _registrationUiEvent.send(RegistrationUiEvent.ShowSnackBar(it)) }
    }

    /**
     * ERROR HANDLING:
     * For the Use cases
     */
    private suspend fun useCaseError(result: UseCaseResult.Error) {
        val uiEvent = UiText.DynamicString(result.exception.message.toString())
        _registrationUiEvent.send(RegistrationUiEvent.ShowSnackBar(uiEvent))
    }
    /** ********************************** USE CASES **********************************************/

    /** ********************************** API CALLS **********************************************/
    private fun initiateRegistration(input: RegistrationInput) {

        viewModelScope.launch {
            loginModuleUseCases.registerUseCase(input).collect { state ->
                when(state){
                    is State.Success -> {
                        log.d(FEATURE_LOGIN,"REGISTRATION API SUCCESS")
                        _registrationUiEvent.send(RegistrationUiEvent.LoaderState(isLoading = false))
                        _registrationUiEvent.send(RegistrationUiEvent.Success)
                    }
                    is State.Loading -> {
                        log.d(FEATURE_LOGIN,"REGISTRATION API LOADING")
                        _registrationUiEvent.send(RegistrationUiEvent.LoaderState(isLoading = true))
                    }
                    is State.Failed -> {
                        log.d(FEATURE_LOGIN,"REGISTRATION API FAILED")
                        _registrationUiEvent.send(RegistrationUiEvent.LoaderState(isLoading = false))
                        useCaseErrorMessage(UiText.DynamicString("Registration failed"))
                    }
                }
            }
        }

    }
    /** ********************************** API CALLS **********************************************/

    private fun registrationInput() : RegistrationInput{
        return RegistrationInput(
            firstName = viewState.firstName, lastName = viewState.lastName,
            email = viewState.email, password = viewState.pwd, confirmPassword = viewState.confirmPwd
        )
    }

}