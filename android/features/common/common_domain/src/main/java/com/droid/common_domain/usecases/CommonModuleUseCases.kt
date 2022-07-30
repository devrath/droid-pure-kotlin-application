package com.droid.common_domain.usecases

import com.droid.common_domain.usecases.module.SaveUserUseCase

data class CommonModuleUseCases(
    // --> ***************** REPO_CALL *******************
    val saveUserUseCase: SaveUserUseCase
    // --> ***************** REPO_CALL *******************
)
