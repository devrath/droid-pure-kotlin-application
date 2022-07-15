package com.droid.login_presentation.vm

import com.droid.login_presentation.states.LoginViewStates
import com.iprayforgod.core.base.BaseViewModel
import com.iprayforgod.core.data.repositories.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginVm @Inject constructor(
    private val preferenceRepository : PreferenceRepository
) : BaseViewModel()  {

    private val _viewState = MutableStateFlow<LoginViewStates>(LoginViewStates.InitialState)
    val viewState = _viewState.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    fun setEmail(name: String) { _email.value = name }

    private val _pwd = MutableStateFlow("")
    val pwd = _pwd.asStateFlow()
    fun setPwd(name: String) { _pwd.value = name }


}