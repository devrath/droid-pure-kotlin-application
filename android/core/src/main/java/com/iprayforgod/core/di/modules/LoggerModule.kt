package com.iprayforgod.core.di.modules

import com.iprayforgod.core.domain.LoggerFeature
import com.iprayforgod.core.modules.logger.implementation.LoggerFeatureImpl
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * How module works: Instance creation happens from the bottom to the top
 * This Module provides the instance of repository to be used
 * Only exception being that Timber and Logger instance is created in the Application class which is a singleton
 */
@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {

    /**
     * STEP-3: ---> <Final Step>
     * Provides a instance of LOGGER-REPOSITORY
     * *********************************************
     * We always inject a repository
     */
    @Provides
    @Singleton
    fun provideRepositoryLogger(store: LoggerFeature) = LoggerRepository(store)

    /**
     *  STEP-2: --->
     * Provides a instance of Logger data store implementation
     * *********************************************
     * We don't inject the instance of implementation directly - instead we provide it to the repository above
     */
    @Provides
    @Singleton
    fun provideLoggerFeature(): LoggerFeature {
        return LoggerFeatureImpl()
    }

    /**
     * STEP-1: --->
     * We don't do this here since we perform this from the application class of the app
     */
}
