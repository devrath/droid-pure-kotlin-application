package com.droid.login_presentation.components.mainComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iprayforgod.core_ui.composables.*

@Composable
fun LoginScreenContent(
    email: String, password: String,
    onEmailChanged : (String) -> Unit,
    onPwdChanged : (String) -> Unit,
    clickSignUp : (Int) -> Unit,
    clickForgotPwd : (Int) -> Unit,
    clickLoginAction : () -> Unit
) {
    LoginPageContent(
        email,password,onEmailChanged,
        onPwdChanged,clickSignUp,clickForgotPwd,clickLoginAction
    )
}

@Composable
fun LoginPageContent(
    email: String, password: String,
    onEmailChanged : (String) -> Unit,
    onPwdChanged : (String) -> Unit,
    clickSignUp : (Int) -> Unit,
    clickForgotPwd : (Int) -> Unit,
    clickLoginAction : () -> Unit
) {

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray)) {

        Box(modifier = Modifier.fillMaxSize()) {
            CustomClickableText(
                contentValue = "Sign up here",
                textColor = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp),
                onClick = clickSignUp
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CustomText(contentValue = "Login", textColor = Color.Black, fontSize = 40.sp)

            Spacer(modifier = Modifier.height(20.dp))
            CustomInput(
                contentValue = email, valueChanged = onEmailChanged,
                params = InputFieldParams.EMAIL
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomInput(
                contentValue = password, valueChanged = onPwdChanged,
                params = InputFieldParams.PASSWORD
            )
            Spacer(modifier = Modifier.height(20.dp))

            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                CustomButton(
                    buttonText = "Login",
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
                   contentValue = "Forgot password",
                   textColor= Color.Black,
                   fontSize = 14.sp,
                   onClick = clickForgotPwd
               )
           }
        }
    }
}




@Composable
@Preview(showBackground = true)
fun LoginPagePreview() { LoginPageContent("userName", "password",{},{},{},{},{}) }