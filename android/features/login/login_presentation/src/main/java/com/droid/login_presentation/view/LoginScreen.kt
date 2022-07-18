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
import com.droid.login_presentation.components.mainComponents.LoginScreenContent
import com.droid.login_domain.usecases.states.LoginViewStates
import com.droid.login_presentation.vm.LoginVm
import com.iprayforgod.core.logger.AppLogger
import com.iprayforgod.core.ui.uiEvent.UiText

@Composable
fun LoginScreen(
    viewModel: LoginVm = hiltViewModel()
) {
    val context = LocalContext.current
    val email = viewModel.email.collectAsState()
    val pwd = viewModel.pwd.collectAsState()

    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        LoginScreenContent(
            email.value, pwd.value, viewModel::setEmail,
            viewModel::setPwd,
            {signUpAction(viewModel)}, {forgotPwdAction(viewModel)}, {loginAction(viewModel)}
        )
    }

    LaunchedEffect(key1 = scaffoldState) {
        viewModel.viewState.collect {
            when (it) {
                is LoginViewStates.InitialState -> {}
                is LoginViewStates.ErrorState -> showMsg(context, scaffoldState, it.errorMessage)
                is LoginViewStates.NoConnectivity -> {}
                is LoginViewStates.LoginValidationSuccessful -> {
                    AppLogger.d("DEBUG")
                    AppLogger.e("DEBUG")
                    AppLogger.w("DEBUG")
                    AppLogger.v("DEBUG")
                    AppLogger.i("DEBUG")
                    Toast.makeText(context, "Validation Successful", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

/**
 * CLICK-ACTION ---> SignUp
 */
fun signUpAction(viewModel: LoginVm) { viewModel.actionSignUp() }
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
    LoginScreenContent("Email", "Password", {}, {}, {}, {}, {})
}