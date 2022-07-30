package com.droid.common_data.di

import com.droid.common_data.service.preferences.SaveUserService
import com.iprayforgod.core.di.qualifiers.IoDispatcher
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.core.modules.parser.repository.ParserRepository
import com.iprayforgod.core.modules.preference.repository.PreferenceRepository
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
        parserRepository: ParserRepository,
        preferenceRepository: PreferenceRepository,
        logService: LoggerRepository,
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