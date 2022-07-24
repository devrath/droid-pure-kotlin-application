package com.droid.login_presentation.vm

import androidx.lifecycle.viewModelScope
import com.droid.login_domain.usecases.cases.LoginModuleUseCases
import com.droid.login_domain.usecases.entities.inputs.RegistrationInput
import com.droid.login_domain.usecases.states.RegistrationViewStates
import com.iprayforgod.core.modules.keys.KeysFeatureNames.FEATURE_LOGIN
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.core.platform.base.BaseViewModel
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.core.platform.functional.UseCaseResult
import com.iprayforgod.core.platform.functional.data
import com.iprayforgod.core.platform.ui.uiEvent.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegistrationVm @Inject constructor(
    private var  loginModuleUseCases: LoginModuleUseCases,
    private var  log: LoggerRepository
) : BaseViewModel() {

    private val _viewState = MutableSharedFlow<RegistrationViewStates>(
        replay = 1,onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val viewState = _viewState.asSharedFlow()

    private var registrationJob: Job? = null

    private val _firstName = MutableStateFlow("")
    val firstName = _firstName.asStateFlow()
    fun setFirstName(firstName: String) { _firstName.value = firstName }

    private val _lastName = MutableStateFlow("")
    val lastName = _lastName.asStateFlow()
    fun setLastName(lastName: String) { _lastName.value = lastName }

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    fun setEmail(name: String) { _email.value = name }

    private val _pwd = MutableStateFlow("")
    val pwd = _pwd.asStateFlow()
    fun setPwd(pwd: String) { _pwd.value = pwd }

    private val _confirmPwd = MutableStateFlow("")
    val confirmPwd = _confirmPwd.asStateFlow()
    fun setConfirmPwd(confirmPwd: String) { _confirmPwd.value = confirmPwd }

    fun actionRegistration() {
        log.d(FEATURE_LOGIN,"ACTION:->  Registration action functionality is invoked")
        viewModelScope.launch {
            val input = registrationInput()

            val registrationValidation = withContext(Dispatchers.Default) { validateFieldsForRegistration(input) }
            if(registrationValidation){
                _viewState.tryEmit(RegistrationViewStates.RegistrationValidationSuccessful)
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
        result?.let {
            _viewState.tryEmit(RegistrationViewStates.ErrorState(errorMessage = it))
        }
    }

    /**
     * ERROR HANDLING:
     * For the Use cases
     */
    private suspend fun useCaseError(result: UseCaseResult.Error) {
        val uiEvent = UiText.DynamicString(result.exception.message.toString())
        _viewState.tryEmit(RegistrationViewStates.ErrorState(errorMessage = uiEvent))
    }
    /** ********************************** USE CASES **********************************************/

    private fun registrationInput() = RegistrationInput(
        firstName = firstName.value.trim(), lastName = lastName.value.trim(),
        email = email.value.trim(), password = pwd.value.trim(),
        confirmPassword = confirmPwd.value.trim()
    )

    fun initiateRegistration() {
        val input = registrationInput()

        viewModelScope.launch {
            loginModuleUseCases.registerUseCase(input).collect { state ->
                when(state){
                    // <state.data> get the content
                    is State.Success -> {
                        log.d(FEATURE_LOGIN,"SUCCESS")
                        log.d(FEATURE_LOGIN,state.data.email)
                        log.d(FEATURE_LOGIN,state.data.firstName)
                        log.d(FEATURE_LOGIN,state.data.lastName)
                        log.d(FEATURE_LOGIN,state.data.id)
                    }
                    is State.Loading -> {
                        log.d(FEATURE_LOGIN,"LOADING")
                    }
                    is State.Failed -> {
                        log.d(FEATURE_LOGIN,"FAILED")
                    }
                }
            }
        }

    }
}