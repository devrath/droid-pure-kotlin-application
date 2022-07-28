package com.droid.login_presentation.view

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
import com.droid.login_presentation.R
import com.droid.login_presentation.components.mainComponents.ForgotPwdPageContent
import com.droid.login_presentation.states.forgotPassword.ForgotPwdViewEvent
import com.droid.login_presentation.vm.ForgotPwdVm
import com.iprayforgod.core.platform.ui.uiEvent.UiEvent


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ForgotPwdScreen(
    viewModel: ForgotPwdVm = hiltViewModel()
) {

    val context = LocalContext.current
    val state = viewModel.viewState

    val scaffoldState = rememberScaffoldState()
    val keyboardController = LocalSoftwareKeyboardController.current

    val headerString = context.resources.getString(R.string.str_forgot_pwd_header)
    val forgotPwdDesc = context.resources.getString(R.string.str_forgot_desc)
    val emailLabel = context.resources.getString(R.string.str_email)
    val strSubmit = context.resources.getString(R.string.str_submit)
    val strEmailSentSuccess = context.resources.getString(R.string.str_email_sent_success)

    Scaffold(scaffoldState = scaffoldState) {
        ForgotPwdPageContent(
            headerStr = headerString, descStr = forgotPwdDesc,
            emailLabel = emailLabel, submitBtnStr = strSubmit, email = state.email,
            onEmailChanged = {
                viewModel.onEvent(ForgotPwdViewEvent.OnViewChangedEmail(it))
            },
            clickSubmitAction = {
                keyboardController?.hide()
                viewModel.onEvent(ForgotPwdViewEvent.OnSubmitClick)
            },
            isLoading = state.isLoaderVisible
        )
    }

    LaunchedEffect(key1 = scaffoldState) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar ->{
                    val msgToShow = event.message.asString(context)
                    scaffoldState.snackbarHostState.showSnackbar(message = msgToShow)
                    keyboardController?.hide()
                }
                is UiEvent.Success -> {
                    Toast.makeText(context,strEmailSentSuccess, Toast.LENGTH_LONG).show()
                }
                else -> Unit
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ForgotPwdContentPreview() {
    val headerStr = "FORGOT PASSWORD?"
    val descStr = "Enter your e-mail address we'll send you a email to reset your password"
    val emailLabel = "Email ID"
    val email = "email"
    val submitBtnStr = "SUBMIT"

    ForgotPwdPageContent(
        headerStr,descStr,
        emailLabel,email,submitBtnStr,
        {}, {}, false
    )
}