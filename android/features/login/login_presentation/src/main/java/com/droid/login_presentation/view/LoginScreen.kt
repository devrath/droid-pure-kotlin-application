package com.droid.login_presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.droid.login_presentation.components.mainComponents.LoginScreenContent
import com.droid.login_presentation.vm.LoginVm

@Composable
fun LoginScreen(
    viewModel: LoginVm = hiltViewModel()
){
   LoginScreenContent()
}

@Composable
@Preview(showBackground = true)
fun LoginScreenContentPreview() {
    LoginScreenContent()
}