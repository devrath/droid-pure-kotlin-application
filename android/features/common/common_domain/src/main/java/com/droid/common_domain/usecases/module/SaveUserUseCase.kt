package com.droid.common_domain.usecases.module

import com.droid.common_domain.repository.CommonRepository
import com.iprayforgod.core.domain.models.User

class SaveUserUseCase(
    private val commonRepository : CommonRepository
) {

    operator fun invoke(user: User) {
        return commonRepository.saveUser(user)
    }

}