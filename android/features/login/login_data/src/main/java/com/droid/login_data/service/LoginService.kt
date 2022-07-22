package com.droid.login_data.service

import com.iprayforgod.core.modules.firebase.repository.FirebaseAuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LoginService @Inject constructor(
    val service : FirebaseAuthRepository
) {




}