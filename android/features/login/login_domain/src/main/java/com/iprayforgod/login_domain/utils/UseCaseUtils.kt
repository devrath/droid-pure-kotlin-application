package com.iprayforgod.login_domain.utils

object UseCaseUtils {

    fun containsLettersAndDigits(password: String) = password.any { it.isDigit() } &&
        password.any { it.isLetter() }
}
