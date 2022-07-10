package com.iprayforgod.core.domain.preferences

interface PreferenceDatastore {

    suspend fun saveIsOnBoardingShownState(text: Boolean)

    suspend fun loadIsOnBoardingShownState() : Boolean

}