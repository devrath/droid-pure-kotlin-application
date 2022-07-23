package com.droid.login_data.repository

import com.droid.login_data.service.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    val loginService : LoginService
) {

    fun  registerUser() {

    }
}