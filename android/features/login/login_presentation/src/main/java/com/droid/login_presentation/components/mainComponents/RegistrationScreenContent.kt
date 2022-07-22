package com.droid.login_presentation.components.mainComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun RegistrationScreenContent(
    firstNameLabel: String,lastNameLabel: String,emailLabel: String,passwordLabel: String,
    confirmPasswordLabel: String, registerHeaderStr: String,registerBtnStr: String,loginTxtStr: String,
    firstName: String, lastName: String, email: String, pwd: String, confirmPwd: String,
    firstNameChanged : (String) -> Unit, lastNameChanged : (String) -> Unit,
    onEmailChanged : (String) -> Unit, onPwdChanged : (String) -> Unit,
    onConfirmPwdChanged : (String) -> Unit,
    clickRegistrationAction : () -> Unit, clickLoginAction : (Int) -> Unit
){
    RegistrationPageContent(
        firstNameLabel,lastNameLabel,emailLabel,passwordLabel,confirmPasswordLabel,
        registerHeaderStr,registerBtnStr,loginTxtStr,
        firstName, lastName, email, pwd, confirmPwd,
        firstNameChanged, lastNameChanged, onEmailChanged, onPwdChanged,onConfirmPwdChanged,
        clickRegistrationAction,clickLoginAction
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegistrationPageContent(
    firstNameLabel: String,lastNameLabel: String,emailLabel: String,passwordLabel: String,
    confirmPasswordLabel: String, registerHeaderStr: String,registerBtnStr: String,loginTxtStr: String,
    firstName: String,
    lastName: String,
    email: String,
    pwd: String,
    confirmPwd: String,
    firstNameChanged: (String) -> Unit,
    lastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPwdChanged: (String) -> Unit,
    onConfirmPwdChanged: (String) -> Unit,
    onRegistrationAction: () -> Unit,
    onClickLoginAction: (Int) -> Unit
) {

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {

        Box(modifier = Modifier.fillMaxSize()) {
            CustomClickableText(
                contentValue = loginTxtStr,
                textColor = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp),
                onClick = onClickLoginAction
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CustomText(contentValue = registerHeaderStr, textColor = Color.Black, fontSize = 25.sp)

            Spacer(modifier = Modifier.height(5.dp))
            CustomInput(
                label = firstNameLabel,
                contentValue = firstName, valueChanged = firstNameChanged,
                params = InputFieldParams.TEXT,
                imeAction = ImeAction.Next,
                keyboardOnNext = { focusManager.moveFocus(FocusDirection.Down) },
                keyboardOnDone = { keyboardController?.hide() }
            )
            Spacer(modifier = Modifier.height(5.dp))
            CustomInput(
                label = lastNameLabel,
                contentValue = lastName, valueChanged = lastNameChanged,
                params = InputFieldParams.TEXT,
                imeAction = ImeAction.Next,
                keyboardOnNext = { focusManager.moveFocus(FocusDirection.Down) },
                keyboardOnDone = { keyboardController?.hide() }
            )
            Spacer(modifier = Modifier.height(5.dp))
            CustomInput(
                label = emailLabel,
                contentValue = email, valueChanged = onEmailChanged,
                params = InputFieldParams.EMAIL,
                imeAction = ImeAction.Next,
                keyboardOnNext = { focusManager.moveFocus(FocusDirection.Down) },
                keyboardOnDone = { keyboardController?.hide() }
            )
            Spacer(modifier = Modifier.height(5.dp))
            CustomInput(
                label = passwordLabel,
                contentValue = pwd, valueChanged = onPwdChanged,
                params = InputFieldParams.PASSWORD,
                imeAction = ImeAction.Next,
                keyboardOnNext = { focusManager.moveFocus(FocusDirection.Down) },
                keyboardOnDone = { keyboardController?.hide() }
            )
            Spacer(modifier = Modifier.height(5.dp))
            CustomInput(
                label = confirmPasswordLabel,
                contentValue = confirmPwd, valueChanged = onConfirmPwdChanged,
                params = InputFieldParams.PASSWORD,
                imeAction = ImeAction.Done,
                keyboardOnNext = { focusManager.moveFocus(FocusDirection.Down) },
                keyboardOnDone = { keyboardController?.hide() }
            )

            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                CustomButton(
                    buttonText = registerBtnStr,
                    isButtonRounded = true,
                    cornersInDp = 50.dp,
                    buttonPaddingInDp = 20.dp,
                    onClickAction = onRegistrationAction
                )
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun RegistrationPagePreview() {
    val firstNameLabel = "First Name"
    val lastNameLabel = "Last Name"
    val emailLabel = "Email"
    val passwordLabel = "Password"
    val confirmPasswordLabel = "Confirm Password"

    val registerHeaderStr = "CREATE AN ACCOUNT"
    val registerBtnStr = "Register"
    val loginTxtStr = "Login"
    RegistrationPageContent(
        firstNameLabel,lastNameLabel,emailLabel,passwordLabel,confirmPasswordLabel,
        registerHeaderStr,registerBtnStr,loginTxtStr,
        "First name", "Last name","Email",
        "Password", "Confirm Password",{},{},{},{},{},{},{}
    )
}
