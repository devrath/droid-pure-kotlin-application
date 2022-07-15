package com.droid.login_presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.droid.login_presentation.components.mainComponents.LoginScreenContent
import com.droid.login_presentation.vm.LoginVm

@Composable
fun LoginScreen(
    viewModel: LoginVm = hiltViewModel()
){
    val email = viewModel.email.collectAsState()
    val pwd = viewModel.pwd.collectAsState()
    LoginScreenContent(email.value,pwd.value,viewModel::setEmail,viewModel::setPwd)
}

@Composable
@Preview(showBackground = true)
fun LoginScreenContentPreview() {
    LoginScreenContent("Email", "Password",{},{})
}