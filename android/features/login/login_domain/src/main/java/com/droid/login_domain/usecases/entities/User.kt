package com.droid.login_domain.usecases.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * A data model class for User with required fields.
 */
@Parcelize
data class User(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val image: String = "",
    val mobile: Long = 0,
    val gender: String = "",
    val profileCompleted: Int = 0
) : Parcelable