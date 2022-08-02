package com.iprayforgod.common_domain.usecases

import com.iprayforgod.common_domain.usecases.module.SaveUserUseCase

data class CommonModuleUseCases(
    // --> ***************** REPO_CALL *******************
    val saveUserUseCase: SaveUserUseCase
    // --> ***************** REPO_CALL *******************
)
