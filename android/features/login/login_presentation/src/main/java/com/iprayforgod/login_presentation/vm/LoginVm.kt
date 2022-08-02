package com.iprayforgod.login_presentation.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.iprayforgod.common_domain.usecases.CommonModuleUseCases
import com.iprayforgod.login_domain.usecases.cases.LoginModuleUseCases
import com.iprayforgod.login_domain.usecases.entities.inputs.LoginInput
import com.iprayforgod.login_presentation.states.login.LoginUiState
import com.iprayforgod.login_presentation.states.login.LoginViewEvent
import com.iprayforgod.login_presentation.states.login.LoginViewResponseEvent
import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.modules.keys.KeysFeatureNames.FEATURE_LOGIN
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.core.platform.base.BaseViewModel
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.core.platform.functional.UseCaseResult
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
    private var loginModuleUseCases: LoginModuleUseCases,
    private var commonModuleUseCases: CommonModuleUseCases,
    private var log: LoggerRepository,
) : BaseViewModel() {

    var viewState by mutableStateOf(LoginUiState())
        private set

    private val _uiEvent = Channel<LoginViewResponseEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: LoginViewEvent) {
        when (event) {
            is LoginViewEvent.OnLoginViewClick -> actionLogin()
            is LoginViewEvent.LoginSaveUserToPreference -> saveUserAfterLoggingIn(event.user)

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
    private fun actionLogin() {
        log.d(FEATURE_LOGIN, "ACTION:->  Login action functionality is invoked")

        viewModelScope.launch {
            val input = loginInput()
            val registrationValidation = withContext(Dispatchers.Default) {
                validateFieldsForLogin(input)
            }
            if (registrationValidation) {
                initiateLoginApi(input)
            }
        }
    }

    private fun saveUserAfterLoggingIn(user: User) {
        log.d(FEATURE_LOGIN, "ACTION:->  Saving user after user logged in")
        viewModelScope.launch { saveUser(user) }
    }
    /** ********************************** BUTTON-ACTIONS *****************************************/

    /** ********************************** USE CASES **********************************************/

    /**
     * USE CASE: use case for email and password field validations
     */
    private suspend fun validateFieldsForLogin(input: LoginInput): Boolean {
        log.d(FEATURE_LOGIN, "USE CASE:->  registration fields validations invoked")
        loginModuleUseCases.validateLogin.invoke(input)
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
     * USE CASE: Saving hte user to preferences
     */
    private fun saveUser(input: User) {
        viewModelScope.launch {
            commonModuleUseCases.saveUserUseCase.invoke(input).collect { state ->
                when (state) {
                    is State.Failed -> {
                        log.d(FEATURE_LOGIN, "LOGIN API FAILED")
                        useCaseErrorMessage(UiText.DynamicString(state.message))
                    }
                    is State.Success -> {
                        log.d(FEATURE_LOGIN, "LOGIN API SUCCESS")
                        _uiEvent.send(LoginViewResponseEvent.SaveUserSuccess)
                    }
                }
            }
        }
    }
    /** ********************************** USE CASES **********************************************/

    /** ********************************** API CALLS **********************************************/
    private fun initiateLoginApi(input: LoginInput) {
        viewModelScope.launch {
            loginModuleUseCases.loginUseCase(input).collect { state ->
                when (state) {
                    is State.Success -> {
                        log.d(FEATURE_LOGIN, "LOGIN API SUCCESS")
                        viewState = viewState.copy(isLoaderVisible = false)
                        _uiEvent.send(LoginViewResponseEvent.LoginApiSuccess(state.data))
                    }
                    is State.Loading -> {
                        log.d(FEATURE_LOGIN, "LOGIN API LOADING")
                        viewState = viewState.copy(isLoaderVisible = true)
                    }
                    is State.Failed -> {
                        log.d(FEATURE_LOGIN, "LOGIN API FAILED")
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
        result?.let { _uiEvent.send(LoginViewResponseEvent.ShowSnackbar(it)) }
    }

    /**
     * ERROR HANDLING:
     * For the Use cases
     */
    private suspend fun useCaseError(result: UseCaseResult.Error) {
        val uiEvent = UiText.DynamicString(result.exception.message.toString())
        _uiEvent.send(LoginViewResponseEvent.ShowSnackbar(uiEvent))
    }
    /** ********************************* DISPLAY MESSAGES ****************************************/

    /** ********************************* INPUTS **************************************************/
    private fun loginInput() = LoginInput(email = viewState.email, password = viewState.pwd)
    /** ********************************* INPUTS **************************************************/
}
