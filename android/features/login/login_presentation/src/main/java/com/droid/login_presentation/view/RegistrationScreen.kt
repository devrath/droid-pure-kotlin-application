package com.droid.login_presentation.view

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.droid.login_domain.usecases.states.RegistrationViewStates
import com.droid.login_presentation.R
import com.droid.login_presentation.components.mainComponents.RegistrationScreenContent
import com.droid.login_presentation.vm.RegistrationVm

@OptIn(ExperimentalComposeUiApi::class)
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

    val firstNameLabel = context.resources.getString(R.string.str_first_name)
    val lastNameLabel = context.resources.getString(R.string.str_last_name)
    val emailLabel = context.resources.getString(R.string.str_email)
    val passwordLabel = context.resources.getString(R.string.str_pwd)
    val confirmPasswordLabel = context.resources.getString(R.string.str_confirm_pwd)
    val registerHeaderStr = context.resources.getString(R.string.str_create_an_account)
    val registerBtnStr = context.resources.getString(R.string.str_register)
    val loginTxtStr = context.resources.getString(R.string.str_login)

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(scaffoldState = scaffoldState) {
        RegistrationScreenContent(
            firstNameLabel,lastNameLabel,emailLabel,passwordLabel,confirmPasswordLabel,
            registerHeaderStr,registerBtnStr,loginTxtStr,
            firstName.value, lastName.value,email.value ,pwd.value,confirmPwd.value,
            viewModel::setFirstName, viewModel::setLastName,
            viewModel::setEmail, viewModel::setPwd, viewModel::setConfirmPwd,
            {
                keyboardController?.hide()
                register(viewModel)
            },
            {
                onLoginClick
            },
            viewModel.loaderVisibility.collectAsState(initial = false).value
        )
    }

    LaunchedEffect(key1 = scaffoldState) {
        viewModel.viewState.collect {
            when (it) {
                is RegistrationViewStates.InitialState -> {

                }
                is RegistrationViewStates.ErrorState -> showMsg(context, scaffoldState, it.errorMessage)
                is RegistrationViewStates.NoConnectivity -> {}
                is RegistrationViewStates.RegistrationValidationSuccessful -> {
                    viewModel.initiateRegistration()
                }
                is RegistrationViewStates.Loading -> { viewModel.updateLoading(it.isLoading) }
                is RegistrationViewStates.RegistrationStatus -> {
                    userRegistrationStatus(context,viewModel,it.isUserRegistered)
                }
            }
        }
    }

}

fun userRegistrationStatus(
    context: Context, viewModel: RegistrationVm, userRegistered: Boolean
) {
    Toast.makeText(context, "User registration successful", Toast.LENGTH_LONG).show()
}

fun register(viewModel: RegistrationVm) {
    viewModel.actionRegistration()
}

@Composable
@Preview(showBackground = true)
fun RegistrationScreenContentPreview() {

    val firstNameLabel = "First Name"
    val lastNameLabel = "Last Name"
    val emailLabel = "Email"
    val passwordLabel = "Password"
    val confirmPasswordLabel = "Confirm Password"

    val registerHeaderStr = "CREATE AN ACCOUNT"
    val registerBtnStr = "Register"
    val loginTxtStr = "Login"


    RegistrationScreenContent(
        firstNameLabel,lastNameLabel,emailLabel,passwordLabel,confirmPasswordLabel,
        registerHeaderStr,registerBtnStr,loginTxtStr,
        "First Name", "Last Name", "Email",
        "Password", "Confirm Password", {}, {}, {}, {}, {},{},{}
    )
}