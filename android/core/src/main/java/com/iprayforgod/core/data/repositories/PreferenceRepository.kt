package com.iprayforgod.core.data.repositories

import com.iprayforgod.core.domain.PreferenceDatastore

class PreferenceRepository(
    val preference: PreferenceDatastore
) {

    /**
     * @param isOnBoardingShown if the on-boarding screen is shown or not
     */
    suspend fun saveTextFromPreferences(isOnBoardingShown: Boolean) {
        preference.saveIsOnBoardingShownState(isOnBoardingShown)
    }

    /**
     * @return if on-boarding screen is shown or not
     */
    suspend fun getTextFromPreferences(): Boolean {
        return preference.loadIsOnBoardingShownState()
    }

}