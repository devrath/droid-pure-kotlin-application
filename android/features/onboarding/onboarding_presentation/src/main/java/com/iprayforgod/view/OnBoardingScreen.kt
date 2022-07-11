package com.iprayforgod.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.iprayforgod.vm.OnBoardingScreenVm

@Composable
fun OnBoardingScreen(
    welcomeViewModel: OnBoardingScreenVm = hiltViewModel()
){

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }

}