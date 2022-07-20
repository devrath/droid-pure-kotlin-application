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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    firstName: String, lastName: String, email: String, pwd: String, confirmPwd: String,
    firstNameChanged : (String) -> Unit, lastNameChanged : (String) -> Unit,
    onEmailChanged : (String) -> Unit, onPwdChanged : (String) -> Unit,
    onConfirmPwdChanged : (String) -> Unit,
    clickRegistrationAction : () -> Unit, clickLoginAction : (Int) -> Unit
){
    RegistrationPageContent(
        firstName, lastName, email, pwd, confirmPwd,
        firstNameChanged, lastNameChanged, onEmailChanged, onPwdChanged,onConfirmPwdChanged,
        clickRegistrationAction,clickLoginAction
    )
}

@Composable
fun RegistrationPageContent(
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

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {

        Box(modifier = Modifier.fillMaxSize()) {
            CustomClickableText(
                contentValue = "Login",
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

            CustomText(contentValue = "CREATE AN ACCOUNT", textColor = Color.Black, fontSize = 25.sp)

            Spacer(modifier = Modifier.height(5.dp))
            CustomInput(
                contentValue = firstName, valueChanged = firstNameChanged,
                params = InputFieldParams.TEXT
            )
            Spacer(modifier = Modifier.height(5.dp))
            CustomInput(
                contentValue = lastName, valueChanged = lastNameChanged,
                params = InputFieldParams.TEXT
            )
            Spacer(modifier = Modifier.height(5.dp))
            CustomInput(
                contentValue = email, valueChanged = onEmailChanged,
                params = InputFieldParams.EMAIL
            )
            Spacer(modifier = Modifier.height(5.dp))
            CustomInput(
                contentValue = pwd, valueChanged = onPwdChanged,
                params = InputFieldParams.PASSWORD
            )
            Spacer(modifier = Modifier.height(5.dp))
            CustomInput(
                contentValue = confirmPwd, valueChanged = onConfirmPwdChanged,
                params = InputFieldParams.PASSWORD
            )

            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                CustomButton(
                    buttonText = "Register",
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
    RegistrationPageContent(
        "First name", "Last name","Email",
        "Password", "Confirm Password",{},{},{},{},{},{},{}
    )
}
