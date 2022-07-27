package com.droid.login_presentation.vm

import androidx.lifecycle.viewModelScope
import com.droid.login_domain.usecases.cases.LoginModuleUseCases
import com.droid.login_domain.usecases.entities.inputs.LoginInput
import com.droid.login_domain.usecases.states.LoginViewStates
import com.iprayforgod.core.modules.keys.KeysFeatureNames.FEATURE_LOGIN
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.core.platform.base.BaseViewModel
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.core.platform.functional.UseCaseResult
import com.iprayforgod.core.platform.functional.data
import com.iprayforgod.core.platform.ui.uiEvent.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginVm @Inject constructor(
    private var  loginModuleUseCases: LoginModuleUseCases,
    private var  log: LoggerRepository,
) : BaseViewModel()  {

    private val _viewState = MutableSharedFlow<LoginViewStates>(
        replay = 1,onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val viewState = _viewState.asSharedFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    fun setEmail(name: String) { _email.value = name }

    private val _pwd = MutableStateFlow("")
    val pwd = _pwd.asStateFlow()
    fun setPwd(name: String) { _pwd.value = name }


    /** ********************************** BUTTON-ACTIONS *****************************************/
    /**
     * ACTION - SignUp
     */
    fun actionSignUp() {
        log.d(FEATURE_LOGIN,"ACTION:->  Sign up action functionality is invoked")

    }

    /**
     * ACTION - Login
     */
    fun actionLogin() {
        log.d(FEATURE_LOGIN,"ACTION:->  Login action functionality is invoked")

        viewModelScope.launch {
            val emailValidation = withContext(Dispatchers.Default) { validateEmail(email.value) }
            if(emailValidation){
                val pwdValidation = withContext(Dispatchers.Default) { validatePassword(pwd.value) }
                if(pwdValidation){
                    _viewState.tryEmit(LoginViewStates.LoginValidationSuccessful)
                }
            }
        }
    }

    /**
     * ACTION - Forgot Password
     */
    fun actionForgotPwd() {
        log.d(FEATURE_LOGIN,"ACTION:->  Forgot password action functionality is invoked")

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
        result?.let {
            _viewState.tryEmit(LoginViewStates.ErrorState(errorMessage = it))
        }
    }

    /**
     * ERROR HANDLING:
     * For the Use cases
     */
    private suspend fun useCaseError(result: UseCaseResult.Error) {
        val uiEvent = UiText.DynamicString(result.exception.message.toString())
        _viewState.tryEmit(LoginViewStates.ErrorState(errorMessage = uiEvent))
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
                        _viewState.tryEmit(LoginViewStates.Loading(isLoading = false))
                        _viewState.tryEmit(LoginViewStates.LoginStatus(isUserLoggedIn = true))
                    }
                    is State.Loading -> {
                        log.d(FEATURE_LOGIN,"LOGIN API LOADING")
                        _viewState.tryEmit(LoginViewStates.Loading(isLoading = true))
                    }
                    is State.Failed -> {
                        log.d(FEATURE_LOGIN,"LOGIN API FAILED")
                        _viewState.tryEmit(LoginViewStates.Loading(isLoading = false))
                        _viewState.tryEmit(LoginViewStates.LoginStatus(isUserLoggedIn = false))
                    }
                }
            }
        }
    }
    /** ********************************** API CALLS **********************************************/

    private fun loginInput() = LoginInput(
        email = email.value.trim(), password = pwd.value.trim()
    )

}