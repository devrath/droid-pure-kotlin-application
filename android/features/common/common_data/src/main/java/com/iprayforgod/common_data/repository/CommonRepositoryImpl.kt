package com.iprayforgod.common_data.repository

import com.iprayforgod.common_data.service.preferences.SaveUserService
import com.iprayforgod.common_domain.repository.CommonRepository
import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.platform.functional.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommonRepositoryImpl @Inject constructor(
    private val saveUserService: SaveUserService
) : CommonRepository {

    // --> SAVE-USER
    override fun saveUser(input: User): Flow<State<Unit>> {
        return saveUserService.saveUser(input)
    }
}
