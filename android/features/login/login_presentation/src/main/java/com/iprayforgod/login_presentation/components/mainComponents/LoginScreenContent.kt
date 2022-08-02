package com.iprayforgod.login_presentation.components.mainComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iprayforgod.core_ui.composables.CustomButton
import com.iprayforgod.core_ui.composables.CustomClickableText
import com.iprayforgod.core_ui.composables.CustomInput
import com.iprayforgod.core_ui.composables.CustomText
import com.iprayforgod.core_ui.composables.InputFieldParams

@Composable
fun LoginScreenContent(
    emailLabel: String,
    pwdLabel: String,
    loginHeaderStr: String,
    loginBtnStr: String,
    forgotPwdTxtStr: String,
    signUpHereTxtStr: String,
    email: String,
    password: String,
    onEmailChanged: (String) -> Unit,
    onPwdChanged: (String) -> Unit,
    clickSignUp: (Int) -> Unit,
    clickForgotPwd: (Int) -> Unit,
    clickLoginAction: () -> Unit,
    isLoading: Boolean = false
) {
    LoginPageContent(
        emailLabel, pwdLabel,
        loginHeaderStr, loginBtnStr, forgotPwdTxtStr, signUpHereTxtStr,
        email, password, onEmailChanged,
        onPwdChanged, clickSignUp, clickForgotPwd, clickLoginAction, isLoading
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginPageContent(
    emailLabel: String,
    pwdLabel: String,
    loginHeaderStr: String,
    loginBtnStr: String,
    forgotPwdTxtStr: String,
    signUpHereTxtStr: String,
    email: String,
    password: String,
    onEmailChanged: (String) -> Unit,
    onPwdChanged: (String) -> Unit,
    clickSignUp: (Int) -> Unit,
    clickForgotPwd: (Int) -> Unit,
    clickLoginAction: () -> Unit,
    isLoading: Boolean
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            if (!isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CustomClickableText(
                        contentValue = signUpHereTxtStr,
                        textColor = Color.Black,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(20.dp),
                        onClick = clickSignUp
                    )
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    CustomText(contentValue = loginHeaderStr, textColor = Color.Black, fontSize = 40.sp)

                    Spacer(modifier = Modifier.height(20.dp))
                    CustomInput(
                        label = emailLabel,
                        contentValue = email, valueChanged = onEmailChanged,
                        params = InputFieldParams.EMAIL,
                        imeAction = ImeAction.Next,
                        keyboardOnNext = { focusManager.moveFocus(FocusDirection.Down) },
                        keyboardOnDone = { keyboardController?.hide() }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomInput(
                        label = pwdLabel,
                        contentValue = password, valueChanged = onPwdChanged,
                        params = InputFieldParams.PASSWORD,
                        imeAction = ImeAction.Done,
                        keyboardOnNext = { focusManager.moveFocus(FocusDirection.Down) },
                        keyboardOnDone = { keyboardController?.hide() }
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        CustomButton(
                            buttonText = loginBtnStr,
                            isButtonRounded = true,
                            cornersInDp = 50.dp,
                            buttonPaddingInDp = 20.dp,
                            onClickAction = clickLoginAction
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        CustomClickableText(
                            contentValue = forgotPwdTxtStr,
                            textColor = Color.Black,
                            fontSize = 14.sp,
                            onClick = clickForgotPwd
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginPagePreview() {
    val emailLabel = "Email"
    val pwdLabel = "Password"

    val loginHeaderStr = "Login"
    val loginBtnStr = "Login"
    val forgotPwdTxtStr = "Forgot Password"
    val signUpHereTxtStr = "Sign up here"

    LoginPageContent(
        emailLabel, pwdLabel,
        loginHeaderStr, loginBtnStr, forgotPwdTxtStr, signUpHereTxtStr,
        "userName", "password",
        {}, {}, {}, {}, {}, false
    )
}
