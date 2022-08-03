package com.iprayforgod.core_mock.domain.models

import com.iprayforgod.core.domain.models.User

object UserMocks {

    fun validUser(): User {
        val userState = User(
            id = "1",
            firstName = "Micheal",
            lastName = "Daniels",
            email = "micheal.daniels123@gmail.com",
            image = "testImage.png",
            mobile = 9811273641,
            gender = "male",
            profileCompleted = 0
        )
        return userState.copy()
    }

    fun invalidUser(): User {
        val userState = User(
            id = "",
            firstName = "",
            lastName = "",
            email = "",
            image = "",
            mobile = 0,
            gender = "",
            profileCompleted = 0
        )
        return userState.copy()
    }

}