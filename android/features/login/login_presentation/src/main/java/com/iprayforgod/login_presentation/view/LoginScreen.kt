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
import com.iprayforgod.login_presentation.components.mainComponents.LoginScreenContent
import com.iprayforgod.login_presentation.states.login.LoginViewEvent
import com.iprayforgod.login_presentation.states.login.LoginViewResponseEvent
import com.iprayforgod.login_presentation.vm.LoginVm

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    onForgotPasswordClick: (Int) -> Unit,
    onSignUpClick: (Int) -> Unit,
    viewModel: LoginVm = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.viewState

    val scaffoldState = rememberScaffoldState()
    val keyboardController = LocalSoftwareKeyboardController.current

    val emailLabel = context.resources.getString(R.string.str_email)
    val passwordLabel = context.resources.getString(R.string.str_pwd)
    val loginHeaderStr = context.resources.getString(R.string.str_header_login)
    val loginBtnStr = context.resources.getString(R.string.str_login)
    val forgotPwdTxtStr = context.resources.getString(R.string.str_forgot_pwd)
    val signUpHereTxtStr = context.resources.getString(R.string.str_sign_up_here)

    Scaffold(scaffoldState = scaffoldState) {
        LoginScreenContent(
            emailLabel, passwordLabel,
            loginHeaderStr, loginBtnStr, forgotPwdTxtStr, signUpHereTxtStr,
            state.email, state.pwd,
            {
                viewModel.onEvent(LoginViewEvent.OnViewChangedEmail(it))
            },
            {
                viewModel.onEvent(LoginViewEvent.OnViewChangedPassword(it))
            },
            onSignUpClick,
            onForgotPasswordClick,
            {
                keyboardController?.hide()
                viewModel.onEvent(LoginViewEvent.OnLoginViewClick)
            },
            state.isLoaderVisible
        )
    }

    LaunchedEffect(key1 = scaffoldState) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is LoginViewResponseEvent.ShowSnackbar -> {
                    val msgToShow = event.message.asString(context)
                    scaffoldState.snackbarHostState.showSnackbar(message = msgToShow)
                    keyboardController?.hide()
                }
                is LoginViewResponseEvent.LoginApiSuccess -> {
                    viewModel.onEvent(LoginViewEvent.LoginSaveUserToPreference(event.user))
                }
                is LoginViewResponseEvent.SaveUserSuccess -> {
                    Toast.makeText(context, "Login Success", Toast.LENGTH_LONG).show()
                }
                else -> Unit
            }
        }
    }
}

/**
 * CLICK-ACTION ---> ForgotPwd
 */
fun forgotPwdAction(viewModel: LoginVm) { }

@Composable
@Preview(showBackground = true)
fun LoginScreenContentPreview() {

    val emailLabel = "Email"
    val pwdLabel = "Password"

    val loginHeaderStr = "Login"
    val loginBtnStr = "Login"
    val forgotPwdTxtStr = "Forgot Password"
    val signUpHereTxtStr = "Sign up here"

    LoginScreenContent(
        emailLabel, pwdLabel,
        loginHeaderStr, loginBtnStr, forgotPwdTxtStr, signUpHereTxtStr,
        "Email", "Password",
        {}, {}, {}, {}, {}
    )
}
