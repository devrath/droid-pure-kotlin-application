package com.iprayforgod.core.modules.preference.repository

import com.iprayforgod.core.domain.features.preferences.PreferenceDatastore
import kotlinx.coroutines.flow.Flow

class PreferenceRepository(
    val preference: PreferenceDatastore
) {

    /**
     * @param isOnBoardingShown if the on-boarding screen is shown or not
     */
    suspend fun saveOnBoardingState(isOnBoardingShown: Boolean) {
        preference.saveOnBoardingState(isOnBoardingShown)
    }

    /**
     * @return if on-boarding screen is shown or not
     */
    suspend fun readOnBoardingState(): Flow<Boolean> {
        return preference.readOnBoardingState()
    }
}
