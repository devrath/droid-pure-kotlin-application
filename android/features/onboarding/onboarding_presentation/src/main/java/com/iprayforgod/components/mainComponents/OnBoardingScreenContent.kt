package com.iprayforgod.components.mainComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.iprayforgod.components.subComponents.OnBoardingFinishButton
import com.iprayforgod.components.subComponents.PagerData
import com.iprayforgod.mock.OnBoardingMockData
import com.iprayforgod.onboarding_domain.models.OnBoardingPageData

@Composable
fun OnBoardingScreenContent(
    data: List<OnBoardingPageData>,
    finishAction: () -> Unit
) {
    CurrScreenOnBoardingContent(data,finishAction)
}

@Preview(showBackground = true)
@Composable
fun PrevOnBoardingContent() {
    CurrScreenOnBoardingContent(OnBoardingMockData.addDataForDemo()) {}
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CurrScreenOnBoardingContent(
    data: List<OnBoardingPageData>,
    finishAction: () -> Unit
) {
    val pagerState = rememberPagerState()
    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            val pageData = data[position]
            PagerData(data = pageData)
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            pagerState = pagerState
        )
        OnBoardingFinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState,
            onClick = finishAction
        )
    }
}
