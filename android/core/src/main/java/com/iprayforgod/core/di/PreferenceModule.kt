package com.iprayforgod.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.iprayforgod.core.data.implementations.PreferenceDatastoreImpl
import com.iprayforgod.core.data.repositories.PreferenceRepository
import com.iprayforgod.core.domain.PreferenceDatastore
import com.iprayforgod.core.keys.KeysPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


/**
 * How module works: Instance creation happens from the bottom to the top
 * This Module provides the instance of repository to be used
 */
@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {


    /**
     * STEP-3: ---> <Final Step>
     * Provides a instance of PREFERENCE-REPOSITORY
     * *********************************************
     * We always inject a repository
     */
    @Provides
    @Singleton
    fun provideRepositoryDatastore(@ApplicationContext context: Context, store: PreferenceDatastore
    ) = PreferenceRepository(preference = store)

    /**
     *  STEP-2: --->
     * Provides a instance of Preference data store implementation
     * *********************************************
     * We don't inject the instance of implementation directly - instead we provide it to the repository above
     */
    @Provides
    @Singleton
    fun provideDataStorePreferences(dataStore: DataStore<Preferences>): PreferenceDatastore {
        return PreferenceDatastoreImpl(dataStore)
    }


    /**
     * STEP-1: --->
     * Provides a instance of Data-Store
     * *********************************************
     * We don't inject the instance of datastore - Instead we provide it to the implementation above
     */
    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(
                SharedPreferencesMigration(appContext, KeysPreferences.SHARED_PREFERENCES_NAME)
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(KeysPreferences.SHARED_PREFERENCES_NAME) }
        )
    }


}