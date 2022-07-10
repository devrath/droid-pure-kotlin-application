package com.iprayforgod.core.preferences.datastore.domain

interface AppDatastore {

    suspend fun saveIsOnBoardingShownState(text: Boolean)

    suspend fun loadIsOnBoardingShownState() : Boolean

}