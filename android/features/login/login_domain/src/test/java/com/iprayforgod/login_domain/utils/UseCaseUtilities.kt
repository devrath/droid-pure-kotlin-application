package com.iprayforgod.login_domain.utils

import com.iprayforgod.login_domain.usecases.entities.inputs.ForgotPwdInput
import com.iprayforgod.login_domain.usecases.entities.inputs.LoginInput
import com.iprayforgod.login_domain.usecases.entities.inputs.RegistrationInput

object UseCaseUtilities {

    fun prepareLoginInput(email: String) : ForgotPwdInput {
        return ForgotPwdInput(email = email)
    }

    fun prepareLoginInput(email: String, password:String) : LoginInput {
        return LoginInput(email = email, password = password)
    }

    fun prepareRegistrationInput(
        firstName: String, lastName:String, email: String,
        password:String, confirmPassword:String
    ) : RegistrationInput {
        return RegistrationInput(
            firstName = firstName, lastName = lastName, email = email, password = password,
            confirmPassword = confirmPassword
        )
    }


}