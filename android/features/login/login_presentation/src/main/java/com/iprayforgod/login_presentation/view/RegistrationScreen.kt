package com.iprayforgod.login_presentation.view

import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.iprayforgod.login_presentation.R
import com.iprayforgod.login_presentation.components.mainComponents.RegistrationScreenContent
import com.iprayforgod.login_presentation.states.registration.RegistrationViewEvent
import com.iprayforgod.login_presentation.vm.RegistrationVm
import com.iprayforgod.core.platform.ui.uiEvent.UiEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegistrationScreen(
    onLoginClick: (Int) -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: RegistrationVm = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.viewState

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
            firstNameLabel, lastNameLabel, emailLabel, passwordLabel, confirmPasswordLabel,
            registerHeaderStr, registerBtnStr, loginTxtStr,
            state.firstName, state.lastName,
            state.email, state.pwd, state.confirmPwd,
            {
                viewModel.onEvent(RegistrationViewEvent.OnViewChangedFirstName(it))
            },
            {
                viewModel.onEvent(RegistrationViewEvent.OnViewChangedLastName(it))
            },
            {
                viewModel.onEvent(RegistrationViewEvent.OnViewChangedEmail(it))
            },
            {
                viewModel.onEvent(RegistrationViewEvent.OnViewChangedPassword(it))
            },
            {
                viewModel.onEvent(RegistrationViewEvent.OnViewChangedConfirmPassword(it))
            },
            {
                keyboardController?.hide()
                viewModel.onEvent(RegistrationViewEvent.OnRegisterViewClick)
            },
            onLoginClick,
            state.isLoaderVisible
        )
    }

    LaunchedEffect(key1 = scaffoldState) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    val msgToShow = event.message.asString(context)
                    scaffoldState.snackbarHostState.showSnackbar(message = msgToShow)
                    keyboardController?.hide()
                }
                UiEvent.Success -> {
                    Toast.makeText(context, "Registration Success", Toast.LENGTH_LONG).show()
                    // Close the registration screen
                    onNavigateUp()
                }
                else -> Unit
            }
        }
    }
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
        firstNameLabel, lastNameLabel, emailLabel, passwordLabel, confirmPasswordLabel,
        registerHeaderStr, registerBtnStr, loginTxtStr,
        "First Name", "Last Name", "Email",
        "Password", "Confirm Password", {}, {}, {}, {}, {}, {}, {}
    )
}
