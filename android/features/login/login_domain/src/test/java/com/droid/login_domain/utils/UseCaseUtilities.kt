package com.droid.login_domain.utils

import com.droid.login_domain.usecases.entities.inputs.ForgotPwdInput
import com.droid.login_domain.usecases.entities.inputs.LoginInput

object UseCaseUtilities {

    fun prepareEmailInput(email: String) : ForgotPwdInput {
        return ForgotPwdInput(email = email)
    }

    fun prepareEmailInput(email: String, password:String) : LoginInput {
        return LoginInput(email = email, password = password)
    }

}