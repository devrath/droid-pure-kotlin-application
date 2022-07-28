package com.droid.login_presentation.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.droid.login_domain.usecases.cases.LoginModuleUseCases
import com.droid.login_domain.usecases.entities.inputs.LoginInput
import com.droid.login_domain.usecases.states.LoginViewStates
import com.droid.login_presentation.states.login.LoginUiState
import com.droid.login_presentation.states.login.LoginViewEvent
import com.iprayforgod.core.modules.keys.KeysFeatureNames.FEATURE_LOGIN
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.core.platform.base.BaseViewModel
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.core.platform.functional.UseCaseResult
import com.iprayforgod.core.platform.functional.data
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
class LoginVm @Inject constructor(
    private var  loginModuleUseCases: LoginModuleUseCases,
    private var  log: LoggerRepository,
) : BaseViewModel()  {

    var viewState by mutableStateOf(LoginUiState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: LoginViewEvent) {
        when(event) {
            is LoginViewEvent.OnLoginViewClick ->  actionLogin()

            is LoginViewEvent.OnViewChangedEmail -> {
                viewState = viewState.copy(email = event.valueEmail)
            }
            is LoginViewEvent.OnViewChangedPassword -> {
                viewState = viewState.copy(pwd = event.valuePwd)
            }
            is LoginViewEvent.OnViewLoaderVisibility -> {
                viewState = viewState.copy(isLoaderVisible = event.isVisible)
            }
        }
    }

    /** ********************************** BUTTON-ACTIONS *****************************************/

    /**
     * ACTION - Login
     */
    fun actionLogin() {
        log.d(FEATURE_LOGIN,"ACTION:->  Login action functionality is invoked")

        viewModelScope.launch {
            val emailValidation = withContext(Dispatchers.Default) { validateEmail(viewState.email) }
            if(emailValidation){
                val pwdValidation = withContext(Dispatchers.Default) { validatePassword(viewState.pwd) }
                if(pwdValidation){
                    _uiEvent.send(UiEvent.Success)
                }
            }
        }
    }
    /** ********************************** BUTTON-ACTIONS *****************************************/

    /** ********************************** USE CASES **********************************************/
    /**
     * USE CASE: use case for email field validations
     */
    private suspend fun validateEmail(email: String): Boolean {
        log.d(FEATURE_LOGIN,"USE CASE:->  validate email is invoked")


        when (val result = loginModuleUseCases.validateEmail.invoke(email)) {
            is UseCaseResult.Success -> {
                val emailValidationResult = result.value.data as LoginViewStates.EmailValidationStatus
                if(emailValidationResult.result.successful){
                    return true
                }else{
                    useCaseErrorMessage(emailValidationResult.result.errorMessage)
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
     * USE CASE: use case for password field validations
     */
    private suspend fun validatePassword(password: String): Boolean {
        log.d(FEATURE_LOGIN,"USE CASE:->  validate password is invoked")

        when (val result = loginModuleUseCases.validatePassword.invoke(password)) {
            is UseCaseResult.Success -> {
                val pwdValidationResult = result.value.data as LoginViewStates.PasswordValidationStatus
                return if(pwdValidationResult.result.successful){
                    true
                }else{
                    useCaseErrorMessage(pwdValidationResult.result.errorMessage)
                    false
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
    private suspend fun useCaseErrorMessage(result:UiText?) {
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
    fun initiateLoginApi() {
        val input = loginInput()
        viewModelScope.launch {
            loginModuleUseCases.loginUseCase(input).collect { state ->
                when(state){
                    is State.Success -> {
                        log.d(FEATURE_LOGIN,"LOGIN API SUCCESS")
                        viewState = viewState.copy(isLoaderVisible = false)
                        _uiEvent.send(UiEvent.Success)
                    }
                    is State.Loading -> {
                        log.d(FEATURE_LOGIN,"LOGIN API LOADING")
                        viewState = viewState.copy(isLoaderVisible = true)
                    }
                    is State.Failed -> {
                        log.d(FEATURE_LOGIN,"LOGIN API FAILED")
                        viewState = viewState.copy(isLoaderVisible = false)
                        useCaseErrorMessage(UiText.DynamicString("Registration failed"))
                    }
                }
            }
        }
    }
    /** ********************************** API CALLS **********************************************/

    private fun loginInput() = LoginInput(email = viewState.email, password = viewState.pwd)


}