package com.iprayforgod.core.domain.preferences

interface AppDatastore {

    suspend fun saveIsOnBoardingShownState(text: Boolean)

    suspend fun loadIsOnBoardingShownState() : Boolean

}