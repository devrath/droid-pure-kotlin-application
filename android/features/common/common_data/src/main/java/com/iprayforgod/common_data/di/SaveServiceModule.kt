package com.iprayforgod.common_data.di

import com.iprayforgod.common_data.service.preferences.SaveUserService
import com.iprayforgod.core.di.qualifiers.IoDispatcher
import com.iprayforgod.core.data.repository.logger.LoggerRepository
import com.iprayforgod.core.data.repository.parser.ParserRepository
import com.iprayforgod.core.data.repository.preference.PreferenceRepository
import com.iprayforgod.core.domain.features.logger.LoggerFeature
import com.iprayforgod.core.domain.features.parser.ParserFeature
import com.iprayforgod.core.domain.features.preferences.PreferenceDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SaveServiceModule {

    @Provides
    @Singleton
    fun provideSaveUserService(
        parserRepository: ParserFeature,
        preferenceRepository: PreferenceDatastore,
        logService: LoggerFeature,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): SaveUserService {
        return SaveUserService(
            parserRepo = parserRepository,
            prefRepo = preferenceRepository,
            log = logService,
            dispatcher = dispatcher
        )
    }
}
