package com.droid.login_presentation.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.droid.login_domain.usecases.cases.LoginModuleUseCases
import com.droid.login_domain.usecases.entities.inputs.ForgotPwdInput
import com.droid.login_presentation.states.forgotPassword.ForgotPwdUiState
import com.droid.login_presentation.states.forgotPassword.ForgotPwdViewEvent
import com.iprayforgod.core.modules.keys.KeysFeatureNames
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

@HiltViewModel
class ForgotPwdVm @Inject constructor(
    private var  loginModuleUseCases: LoginModuleUseCases,
    private var  log: LoggerRepository,
) : BaseViewModel()  {

    var viewState by mutableStateOf(ForgotPwdUiState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ForgotPwdViewEvent) {
        when(event) {
            is ForgotPwdViewEvent.OnSubmitClick ->  actionSubmit()
            is ForgotPwdViewEvent.OnViewChangedEmail -> {
                viewState = viewState.copy(email = event.valueEmail)
            }
            is ForgotPwdViewEvent.OnViewLoaderVisibility -> {
                viewState = viewState.copy(isLoaderVisible = event.isVisible)
            }
        }
    }

    private fun actionSubmit() {
        val input = forgotPwdInput()
        viewModelScope.launch {
            val forgotPwdValidation = withContext(Dispatchers.Default) {
                validateFieldsForForgotPassword(input)
            }
            if(forgotPwdValidation){
                initiateForgotPwdApi(input)
            }
        }
    }

    /** ********************************** USE CASES **********************************************/

    /**
     * USE CASE: use case for email field validation
     */
    private suspend fun validateFieldsForForgotPassword(input: ForgotPwdInput): Boolean {
        log.d(KeysFeatureNames.FEATURE_LOGIN,"USE CASE:->  forgot password fields validations invoked")
        loginModuleUseCases.validateForgotPassword.invoke(input)
            .onSuccess {
                return if(it.successful){
                    true
                }else{
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
    /** ********************************** USE CASES **********************************************/

    /** ********************************** API CALLS **********************************************/
    private fun initiateForgotPwdApi(input: ForgotPwdInput) {
        viewModelScope.launch {
            loginModuleUseCases.forgotPwdUseCase(input).collect { state ->
                when(state){
                    is State.Success -> {
                        log.d(KeysFeatureNames.FEATURE_LOGIN,"FORGOT API SUCCESS")
                        viewState = viewState.copy(isLoaderVisible = false)
                        _uiEvent.send(UiEvent.Success)
                    }
                    is State.Loading -> {
                        log.d(KeysFeatureNames.FEATURE_LOGIN,"FORGOT API LOADING")
                        viewState = viewState.copy(isLoaderVisible = true)
                    }
                    is State.Failed -> {
                        log.d(KeysFeatureNames.FEATURE_LOGIN,"FORGOT API FAILED")
                        viewState = viewState.copy(isLoaderVisible = false)
                        useCaseErrorMessage(UiText.DynamicString(state.message))
                    }
                }
            }
        }
    }
    /** ********************************** API CALLS **********************************************/

    /** ********************************* DISPLAY MESSAGES ****************************************/
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
    /** ********************************* DISPLAY MESSAGES ****************************************/

    /** ********************************* INPUTS **************************************************/
    private fun forgotPwdInput() = ForgotPwdInput(email = viewState.email)
    /** ********************************* INPUTS **************************************************/

}