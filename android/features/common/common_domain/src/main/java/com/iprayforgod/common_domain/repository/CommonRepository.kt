package com.iprayforgod.common_domain.repository

import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.platform.functional.State
import kotlinx.coroutines.flow.Flow

interface CommonRepository {
    fun saveUser(input: User): Flow<State<Unit>>
}
