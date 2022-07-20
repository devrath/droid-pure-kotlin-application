package com.droid.login_presentation.view

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.droid.login_presentation.components.mainComponents.RegistrationScreenContent
import com.droid.login_presentation.vm.RegistrationVm

@Composable
fun RegistrationScreen(
    onLoginClick: () -> Unit,
    viewModel : RegistrationVm = hiltViewModel()
){
    val context = LocalContext.current
    val firstName = viewModel.firstName.collectAsState()
    val lastName = viewModel.lastName.collectAsState()
    val email = viewModel.email.collectAsState()
    val pwd = viewModel.pwd.collectAsState()
    val confirmPwd = viewModel.confirmPwd.collectAsState()

    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        RegistrationScreenContent(
            firstName.value, lastName.value,email.value ,pwd.value,confirmPwd.value,
            viewModel::setFirstName, viewModel::setLastName,
            viewModel::setEmail, viewModel::setPwd, viewModel::setConfirmPwd,
            {register(viewModel)},{onLoginClick}
        )
    }

}

fun register(viewModel: RegistrationVm) {
    viewModel.initiateRegistration()
}

@Composable
@Preview(showBackground = true)
fun RegistrationScreenContentPreview() {
    RegistrationScreenContent(
        "First Name", "Last Name", "Email",
        "Password", "Confirm Password", {}, {}, {}, {}, {},{},{}
    )
}