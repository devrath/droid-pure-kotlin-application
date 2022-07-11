package com.iprayforgod.core.domain

interface PreferenceDatastore {

    suspend fun saveIsOnBoardingShownState(text: Boolean)

    suspend fun loadIsOnBoardingShownState() : Boolean

}