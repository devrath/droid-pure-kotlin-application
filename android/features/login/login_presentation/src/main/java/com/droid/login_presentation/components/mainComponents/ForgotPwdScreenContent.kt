package com.droid.login_presentation.components.mainComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iprayforgod.core_ui.composables.CustomButton
import com.iprayforgod.core_ui.composables.CustomInput
import com.iprayforgod.core_ui.composables.CustomText
import com.iprayforgod.core_ui.composables.InputFieldParams

@Composable
fun ForgotPwdScreenContent(
    headerStr: String,
    descStr: String,
    emailLabel: String,
    email: String,
    submitBtnStr: String,
    onEmailChanged: (String) -> Unit,
    clickSubmitAction: () -> Unit,
    isLoading: Boolean = false
) {
    ForgotPwdPageContent(
        headerStr, descStr,
        emailLabel, email, submitBtnStr, onEmailChanged, clickSubmitAction, isLoading
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ForgotPwdPageContent(
    headerStr: String,
    descStr: String,
    emailLabel: String,
    email: String,
    submitBtnStr: String,
    onEmailChanged: (String) -> Unit,
    clickSubmitAction: () -> Unit,
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

                Column(
                    modifier = Modifier.fillMaxSize().padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    CustomText(
                        contentValue = headerStr, textColor = Color.Black,
                        fontSize = 22.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CustomText(
                        contentValue = descStr, textColor = Color.Black,
                        fontSize = 14.sp, textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    CustomInput(
                        label = emailLabel,
                        contentValue = email, valueChanged = onEmailChanged,
                        params = InputFieldParams.EMAIL,
                        imeAction = ImeAction.Next,
                        keyboardOnNext = { focusManager.moveFocus(FocusDirection.Down) },
                        keyboardOnDone = { keyboardController?.hide() }
                    )

                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        CustomButton(
                            buttonText = submitBtnStr,
                            isButtonRounded = true,
                            cornersInDp = 50.dp,
                            buttonPaddingInDp = 20.dp,
                            onClickAction = clickSubmitAction
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ForgotPwdPagePreview() {
    val headerStr = "FORGOT PASSWORD?"
    val descStr = "Enter your e-mail address we'll send you a email to reset your password"
    val emailLabel = "Email ID"
    val email = "email"
    val submitBtnStr = "SUBMIT"

    ForgotPwdPageContent(
        headerStr, descStr, emailLabel, email, submitBtnStr,
        {}, {}, false
    )
}
