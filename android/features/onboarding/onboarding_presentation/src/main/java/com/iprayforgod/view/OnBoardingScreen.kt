package com.iprayforgod.view

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.iprayforgod.components.mainComponents.OnBoardingScreenContent
import com.iprayforgod.vm.OnBoardingScreenVm

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingScreenVm = hiltViewModel()
){
    OnBoardingScreenContent(viewModel.onBoardingPageData)
}