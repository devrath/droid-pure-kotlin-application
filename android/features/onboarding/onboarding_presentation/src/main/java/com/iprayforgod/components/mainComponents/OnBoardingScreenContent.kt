package com.iprayforgod.components.mainComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.iprayforgod.mock.OnBoardingMockData
import com.iprayforgod.onboarding_domain.models.OnBoardingPageData
import com.iprayforgod.vm.OnBoardingScreenVm

@Composable
fun OnBoardingScreenContent(data: List<OnBoardingPageData>) {
    CurrScreenOnBoardingContent(data)
}

@Preview(showBackground = true)
@Composable
fun PrevOnBoardingContent() { CurrScreenOnBoardingContent(OnBoardingMockData.addDataForDemo()) }

@Composable
fun CurrScreenOnBoardingContent(data: List<OnBoardingPageData>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

    }
}
