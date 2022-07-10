package com.iprayforgod.core.data.implementations.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.iprayforgod.core.domain.preferences.PreferenceDatastore
import com.iprayforgod.core.keys.KeysPreferences.KEY_TEXT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

class PreferenceDatastoreImpl(
    private val dataStore: DataStore<Preferences>
): PreferenceDatastore {

    companion object {
        private val KEY_REF_isOnBoardingShown = booleanPreferencesKey(KEY_TEXT)
    }

    override suspend fun saveIsOnBoardingShownState(state: Boolean) {
        dataStore.edit { it[KEY_REF_isOnBoardingShown] = state }
    }

    override suspend  fun loadIsOnBoardingShownState(): Boolean {
        return dataStore.getValueFlow(KEY_REF_isOnBoardingShown, false).first()
    }

    private fun <T> DataStore<Preferences>.getValueFlow(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): Flow<T> {
        return this.data
            .catch { exception ->
                if (exception is IOException) { emit(emptyPreferences()) } else { throw exception }
            }.map { preferences ->
                preferences[key] ?: defaultValue
            }
    }

}