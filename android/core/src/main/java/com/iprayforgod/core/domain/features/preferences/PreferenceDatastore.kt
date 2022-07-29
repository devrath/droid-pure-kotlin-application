package com.iprayforgod.core.domain.features.preferences

import kotlinx.coroutines.flow.Flow

interface PreferenceDatastore {

    suspend fun saveCurrentUser(text: String)
    suspend fun readCurrentUser(): Flow<String>

    suspend fun saveOnBoardingState(text: Boolean)
    suspend fun readOnBoardingState(): Flow<Boolean>

}
