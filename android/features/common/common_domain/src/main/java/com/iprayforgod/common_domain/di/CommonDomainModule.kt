package com.iprayforgod.common_domain.di

import com.iprayforgod.common_domain.repository.CommonRepository
import com.iprayforgod.common_domain.usecases.CommonModuleUseCases
import com.iprayforgod.common_domain.usecases.module.SaveUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CommonDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        commonRepo: CommonRepository
    ): CommonModuleUseCases {
        return CommonModuleUseCases(
            saveUserUseCase = SaveUserUseCase(commonRepository = commonRepo)
        )
    }
}
