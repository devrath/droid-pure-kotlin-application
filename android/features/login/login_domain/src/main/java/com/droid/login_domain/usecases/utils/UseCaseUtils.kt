package com.droid.login_domain.usecases.utils

object UseCaseUtils {

     fun containsLettersAndDigits(password: String) = password.any { it.isDigit() } &&
        password.any { it.isLetter() }

}