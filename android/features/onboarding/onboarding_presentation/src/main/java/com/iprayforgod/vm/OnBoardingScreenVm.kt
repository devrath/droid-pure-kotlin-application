package com.iprayforgod.vm

import androidx.lifecycle.viewModelScope
import com.iprayforgod.core.base.BaseViewModel
import com.iprayforgod.core.data.repositories.PreferenceRepository
import com.iprayforgod.onboarding_domain.models.OnBoardingPageData
import com.iprayforgod.onboarding_presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingScreenVm @Inject constructor(
    private val preferenceRepository : PreferenceRepository
) : BaseViewModel() {

    val onBoardingPageData = mutableListOf<OnBoardingPageData>()

    init {
        //addDataForDemo()
    }



    /**
     * Save the state of the on-boarding into the preferences via the repository
     */
    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            preferenceRepository.saveOnBoardingState(isOnBoardingShown = completed)
        }
    }


}