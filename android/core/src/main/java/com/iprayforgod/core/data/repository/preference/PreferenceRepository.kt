package com.iprayforgod.core.data.repository.preference

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

    /**
     * @param user details of the logged in user
     */
    suspend fun saveUserState(user: String) {
        preference.saveCurrentUser(user)
    }

    /**
     * @return the user data that is being saved
     */
    suspend fun readUserState(): Flow<String> {
        return preference.readCurrentUser()
    }
}
