package com.iprayforgod.core.domain.features.preferences

import kotlinx.coroutines.flow.Flow

interface PreferenceDatastore {

    suspend fun saveOnBoardingState(text: Boolean)

    suspend fun readOnBoardingState(): Flow<Boolean>
}
