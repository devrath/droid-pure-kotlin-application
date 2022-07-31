package com.iprayforgod.core.modules.preference.implementation

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.iprayforgod.core.domain.features.preferences.PreferenceDatastore
import com.iprayforgod.core.modules.keys.KeysPreferences.KEY_TEXT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PreferenceDatastoreImpl(
    private val dataStore: DataStore<Preferences>
) : PreferenceDatastore {

    companion object {
        private val KEY_REF_isOnBoardingShown = booleanPreferencesKey(KEY_TEXT)
        private val KEY_REF_currentUserDetails = stringPreferencesKey(KEY_TEXT)
    }

    /** *************************************************************** **/
    override suspend fun saveOnBoardingState(state: Boolean) {
        dataStore.edit { it[KEY_REF_isOnBoardingShown] = state }
    }

    override suspend fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.getValueFlow(KEY_REF_isOnBoardingShown, false)
    }
    /** ************************************************************** **/

    /** ************************************************************** **/
    override suspend fun saveCurrentUser(state: String) {
        dataStore.edit { it[KEY_REF_currentUserDetails] = state }
    }

    override suspend fun readCurrentUser(): Flow<String> {
        return dataStore.getValueFlow(KEY_REF_currentUserDetails, "")
    }
    /** ************************************************************** **/

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
