package com.droid.common_data.di

import com.droid.common_data.repository.CommonRepositoryImpl
import com.droid.common_data.service.preferences.SaveUserService
import com.droid.common_domain.repository.CommonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SaveDataModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        saveUserService : SaveUserService
    ): CommonRepository {
        return CommonRepositoryImpl(
            saveUserService = saveUserService
        )
    }


}