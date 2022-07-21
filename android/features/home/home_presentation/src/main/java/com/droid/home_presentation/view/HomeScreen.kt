package com.droid.home_presentation.view

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.droid.home_presentation.vm.HomeVm

@Composable
fun HomeScreen(
    viewModel: HomeVm = hiltViewModel()
) {

    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {

    }

}