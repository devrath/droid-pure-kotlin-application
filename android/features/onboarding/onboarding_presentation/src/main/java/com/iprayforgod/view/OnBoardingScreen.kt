@file:OptIn(ExperimentalPagerApi::class)

package com.iprayforgod.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.*
import com.iprayforgod.components.mainComponents.OnBoardingScreenContent
import com.iprayforgod.components.subComponents.OnBoardingFinishButton
import com.iprayforgod.components.subComponents.PagerData
import com.iprayforgod.onboarding_domain.models.OnBoardingPageData
import com.iprayforgod.onboarding_presentation.R
import com.iprayforgod.vm.OnBoardingScreenVm

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingScreenVm = hiltViewModel()
){
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            val pageData = viewModel.onBoardingPageData[position]
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
            pagerState = pagerState
        ){
            viewModel.saveOnBoardingState(completed = true)
            /* navController.popBackStack()
            navController.navigate(Screen.Home.route)*/
            // ---------------------------------------------> Perform some action
        }
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