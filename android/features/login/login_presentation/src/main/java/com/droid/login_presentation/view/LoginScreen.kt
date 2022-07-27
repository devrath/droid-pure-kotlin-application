package com.droid.login_presentation.view

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.droid.login_domain.usecases.states.LoginViewStates
import com.droid.login_presentation.R
import com.droid.login_presentation.components.mainComponents.LoginScreenContent
import com.droid.login_presentation.vm.LoginVm
import com.iprayforgod.core.platform.ui.uiEvent.UiText

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit, onSignUpClick: (Int) -> Unit,
    viewModel: LoginVm = hiltViewModel()
) {
    val context = LocalContext.current
    val email = viewModel.email.collectAsState()
    val pwd = viewModel.pwd.collectAsState()

    val scaffoldState = rememberScaffoldState()

    val emailLabel = context.resources.getString(R.string.str_email)
    val passwordLabel = context.resources.getString(R.string.str_pwd)
    val loginHeaderStr = context.resources.getString(R.string.str_header_login)
    val loginBtnStr = context.resources.getString(R.string.str_login)
    val forgotPwdTxtStr = context.resources.getString(R.string.str_forgot_pwd)
    val signUpHereTxtStr = context.resources.getString(R.string.str_sign_up_here)

    Scaffold(scaffoldState = scaffoldState) {
        LoginScreenContent(
            emailLabel,passwordLabel,
            loginHeaderStr,loginBtnStr,forgotPwdTxtStr,signUpHereTxtStr,
            email.value, pwd.value,
            viewModel::setEmail,
            viewModel::setPwd,
            onSignUpClick,
            {
                forgotPwdAction(viewModel)
            },
            {
                loginAction(viewModel)
            },
            viewModel.loaderVisibility.collectAsState(initial = false).value
        )
    }

    LaunchedEffect(key1 = scaffoldState) {
        viewModel.viewState.collect {
            when (it) {
                is LoginViewStates.InitialState -> {}
                is LoginViewStates.ErrorState -> showMsg(context, scaffoldState, it.errorMessage)
                is LoginViewStates.NoConnectivity -> {}
                is LoginViewStates.LoginValidationSuccessful -> {
                    viewModel.initiateLoginApi()
                }
                is LoginViewStates.Loading -> {
                    viewModel.updateLoading(it.isLoading)
                }
                is LoginViewStates.LoginStatus -> {
                    userLoginStatus(context,viewModel,it.isUserLoggedIn)
                }
            }
        }
    }
}

fun userLoginStatus(context: Context, viewModel: LoginVm, userLoggedIn: Boolean) {
    if(userLoggedIn){
        Toast.makeText(context, "User login successful", Toast.LENGTH_LONG).show()
    }else{
        Toast.makeText(context, "User login failure", Toast.LENGTH_LONG).show()
    }
}

/**
 * CLICK-ACTION ---> Login
 */
fun loginAction(viewModel: LoginVm) { viewModel.actionLogin() }
/**
 * CLICK-ACTION ---> ForgotPwd
 */
fun forgotPwdAction(viewModel: LoginVm) { viewModel.actionForgotPwd() }


/**
 * Displaying the snack-bar message
 */
suspend fun showMsg(
    context: Context,
    scaffoldState: ScaffoldState,
    errorMessage: UiText
) {
    scaffoldState.snackbarHostState.showSnackbar(message = errorMessage.asString(context))
}

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
        emailLabel,pwdLabel,
        loginHeaderStr,loginBtnStr,forgotPwdTxtStr,signUpHereTxtStr,
        "Email", "Password",
        {}, {}, {}, {}, {}
    )
}