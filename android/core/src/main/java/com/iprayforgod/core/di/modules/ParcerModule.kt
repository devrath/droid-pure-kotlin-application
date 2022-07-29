package com.iprayforgod.core.di.modules

import com.iprayforgod.core.domain.features.logger.LoggerFeature
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ParcerModule {

    @Provides
    @Singleton
    fun provideMoshi(store: LoggerFeature) = Moshi.Builder().build()
}