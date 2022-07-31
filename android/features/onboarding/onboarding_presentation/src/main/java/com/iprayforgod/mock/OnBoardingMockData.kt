package com.iprayforgod.mock

import com.iprayforgod.onboarding_domain.models.OnBoardingPageData
import com.iprayforgod.onboarding_presentation.R

object OnBoardingMockData {

    fun addDataForDemo(): ArrayList<OnBoardingPageData> {
        val onBoardingPageData = ArrayList<OnBoardingPageData>()
        onBoardingPageData.add(
            OnBoardingPageData(
                image = R.drawable.ic_launcher_icon,
                title = "Title-1",
                description = "Description-1"
            )
        )
        onBoardingPageData.add(
            OnBoardingPageData(
                image = R.drawable.ic_launcher_icon,
                title = "Title-2",
                description = "Description-2"
            )
        )
        onBoardingPageData.add(
            OnBoardingPageData(
                image = R.drawable.ic_launcher_icon,
                title = "Title-3",
                description = "Description-3"
            )
        )
        return onBoardingPageData
    }
}
