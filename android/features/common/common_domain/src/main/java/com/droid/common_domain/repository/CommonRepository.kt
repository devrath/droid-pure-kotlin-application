package com.droid.common_domain.repository

import com.iprayforgod.core.domain.models.User

interface CommonRepository {
    fun saveUser(input: User)
}