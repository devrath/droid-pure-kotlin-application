@file:OptIn(ExperimentalPagerApi::class)

package com.iprayforgod.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.*
import com.iprayforgod.components.mainComponents.OnBoardingScreenContent
import com.iprayforgod.components.subComponents.PagerData
import com.iprayforgod.onboarding_domain.models.OnBoardingPageData
import com.iprayforgod.onboarding_presentation.R
import com.iprayforgod.vm.OnBoardingScreenVm

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingScreenVm = hiltViewModel()
){
    OnBoardingScreenContent(viewModel.onBoardingPageData){
        viewModel.saveOnBoardingState(completed = true)
    }
}


@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerData(
            data =  OnBoardingPageData(
                image = R.drawable.ic_launcher_icon,
                title = "Title-1",
                description = "Description-1"
            )
        )
    }
}


@Composable
@Preview(showBackground = true)
fun SecondOnBoardingScreenPreview() {
    PagerData(
        data =  OnBoardingPageData(
            image = R.drawable.ic_launcher_icon,
            title = "Title-2",
            description = "Description-2"
        )
    )
}

@Composable
@Preview(showBackground = true)
fun ThirdOnBoardingScreenPreview() {
    PagerData(
        data =  OnBoardingPageData(
            image = R.drawable.ic_launcher_icon,
            title = "Title-3",
            description = "Description-1"
        )
    )
}