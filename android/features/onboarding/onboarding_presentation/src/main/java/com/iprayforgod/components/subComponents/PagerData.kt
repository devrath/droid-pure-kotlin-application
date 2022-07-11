package com.iprayforgod.components.subComponents

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.iprayforgod.components.mainComponents.CurrScreenOnBoardingContent
import com.iprayforgod.mock.OnBoardingMockData
import com.iprayforgod.onboarding_domain.models.OnBoardingPageData
import com.iprayforgod.onboarding_presentation.R

@Composable
fun PagerData(data: OnBoardingPageData) {
    CurrPagerDataContent(data)
}

@Composable
fun CurrPagerDataContent(data: OnBoardingPageData) {

}

@Preview(showBackground = true)
@Composable
fun PrevPagerDataContent() {
    CurrPagerDataContent(OnBoardingPageData(
        image = R.drawable.ic_launcher_icon, title = "Demo Title", description = "Demo Description"
    ))
}
