package com.droid.login_presentation.vm

import com.iprayforgod.core.base.BaseViewModel
import com.iprayforgod.core.data.repositories.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginVm @Inject constructor(
    private val preferenceRepository : PreferenceRepository
) : BaseViewModel()  {

}