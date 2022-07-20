package com.droid.login_presentation.vm

import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.core.platform.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegistrationVm @Inject constructor(
    private var  log: LoggerRepository
) : BaseViewModel() {

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




    fun initiateRegistration() {
        TODO("Not yet implemented")
    }
}