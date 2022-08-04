package com.droid.core_mock.core.domain.models

import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_EMAIL
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_FIRST_NAME
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_GENDER
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_ID
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_IMAGE
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_LAST_NAME
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_MOBILE
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_PROFILE_COMPLETED
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_EMAIL
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_FIRST_NAME
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_GENDER
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_ID
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_IMAGE
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_LAST_NAME
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_MOBILE
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_PROFILE_COMPLETED
import com.iprayforgod.core.domain.models.User

object UserMocks {

    object MockCredentials {

        object ValidCredentials {
           const val VALID_ID = "1"
           const val VALID_FIRST_NAME = "Micheal"
           const val VALID_LAST_NAME = "Daniels"
           const val VALID_EMAIL = "micheal.daniels123@gmail.com"
           const val VALID_IMAGE = "testImage.png"
           const val VALID_MOBILE = 9811273641
           const val VALID_GENDER = "male"
           const val VALID_PROFILE_COMPLETED = 0
           const val VALID_PASSWORD = "Hello!2345"
           const val VALID_CONFIRM_PASSWORD = "Hello!2345"

        }

       object InvalidCredentials {
           const val IN_VALID_ID = ""
           const val IN_VALID_FIRST_NAME = ""
           const val IN_VALID_LAST_NAME = ""
           const val IN_VALID_EMAIL = ""
           const val IN_VALID_IMAGE = ""
           const val IN_VALID_MOBILE  : Long = 0
           const val IN_VALID_GENDER = ""
           const val IN_VALID_PROFILE_COMPLETED = 1
           const val IN_VALID_PASSWORD = "-"
           const val IN_VALID_CONFIRM_PASSWORD = "/"
           const val IN_VALID_EMAIL_FORMAT = "micheal.daniels123gmail.com"
           const val IN_VALID_PASSWORD_LENGTH = "12"
       }

    }


    fun validUser(): User {
        val userState = User(
            id = VALID_ID,
            firstName = VALID_FIRST_NAME,
            lastName = VALID_LAST_NAME,
            email = VALID_EMAIL,
            image = VALID_IMAGE,
            mobile = VALID_MOBILE,
            gender = VALID_GENDER,
            profileCompleted = VALID_PROFILE_COMPLETED
        )
        return userState.copy()
    }

    fun invalidUser(): User {
        val userState = User(
            id = IN_VALID_ID,
            firstName = IN_VALID_FIRST_NAME,
            lastName = IN_VALID_LAST_NAME,
            email = IN_VALID_EMAIL,
            image = IN_VALID_IMAGE,
            mobile = IN_VALID_MOBILE,
            gender = IN_VALID_GENDER,
            profileCompleted = IN_VALID_PROFILE_COMPLETED
        )
        return userState.copy()
    }

}