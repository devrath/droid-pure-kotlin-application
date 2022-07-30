package com.droid.common_domain.usecases.module

import com.droid.common_domain.repository.CommonRepository
import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.platform.functional.State
import kotlinx.coroutines.flow.Flow

class SaveUserUseCase(
    private val commonRepository : CommonRepository
) {

    operator fun invoke(user: User): Flow<State<Unit>> {
        return commonRepository.saveUser(user)
    }

}