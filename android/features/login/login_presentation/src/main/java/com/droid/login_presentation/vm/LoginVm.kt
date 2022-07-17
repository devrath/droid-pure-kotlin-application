package com.droid.login_presentation.vm

import androidx.lifecycle.viewModelScope
import com.droid.login_domain.usecases.cases.login.ValidateEmailUseCase
import com.droid.login_domain.usecases.cases.login.ValidatePasswordUseCase
import com.droid.login_domain.usecases.states.LoginViewStates
import com.iprayforgod.core.base.BaseViewModel
import com.iprayforgod.core.functional.UseCaseResult
import com.iprayforgod.core.functional.data
import com.iprayforgod.core.ui.uiEvent.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginVm @Inject constructor(
    private var  validateEmailUseCase: ValidateEmailUseCase,
    private var  validatePasswordUseCase: ValidatePasswordUseCase,
) : BaseViewModel()  {

    private val _viewState = MutableStateFlow<LoginViewStates>(LoginViewStates.InitialState)
    val viewState = _viewState.asStateFlow()

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

    }

    /**
     * ACTION - Login
     */
    fun actionLogin() {
        viewModelScope.launch {
            val emailValidation = withContext(Dispatchers.Default) { validateEmail(email.value) }
            if(emailValidation){
                val pwdValidation = withContext(Dispatchers.Default) { validatePassword(pwd.value) }
                if(pwdValidation){
                    _viewState.value = LoginViewStates.LoginValidationSuccessful
                }
            }
        }
    }

    /**
     * ACTION - Forgot Password
     */
    fun actionForgotPwd() {

    }
    /** ********************************** BUTTON-ACTIONS *****************************************/

    /** ********************************** USE CASES **********************************************/
    /**
     * USE CASE: use case for email field validations
     */
    private suspend fun validateEmail(email: String): Boolean {
        when (val result = validateEmailUseCase.invoke(email)) {
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
        when (val result = validatePasswordUseCase.invoke(password)) {
            is UseCaseResult.Success -> {
                val pwdValidationResult = result.value.data as LoginViewStates.PasswordValidationStatus
                if(pwdValidationResult.result.successful){
                    return true
                }else{
                    useCaseErrorMessage(pwdValidationResult.result.errorMessage)
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
    private suspend fun useCaseErrorMessage(result:UiText?) {
        result?.let {
            _viewState.value = LoginViewStates.ErrorState(errorMessage = it)
        }
    }

    /**
     * ERROR HANDLING:
     * For the Use cases
     */
    private suspend fun useCaseError(result: UseCaseResult.Error) {
        val uiEvent = UiText.DynamicString(result.exception.message.toString())
        _viewState.value = LoginViewStates.ErrorState(errorMessage = uiEvent)
    }
    /** ********************************** USE CASES **********************************************/


}