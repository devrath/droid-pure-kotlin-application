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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iprayforgod.core_ui.composables.*

@Composable
fun LoginScreenContent(
    email: String, password: String,
    onEmailChanged : (String) -> Unit,
    onPwdChanged : (String) -> Unit
) {
    LoginPageContent(email,password,onEmailChanged,onPwdChanged)
}

@Composable
fun LoginPageContent(
    email: String, password: String,
    onEmailChanged : (String) -> Unit,
    onPwdChanged : (String) -> Unit
) {

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray)) {

        Box(modifier = Modifier.fillMaxSize()) {
            ClickableText(
                text = AnnotatedString("Sign up here"),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp),
                onClick = { },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    textDecoration = TextDecoration.Underline,
                    color = Color.Black
                )
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Login",
                style = TextStyle(
                    fontSize = 40.sp,
                    fontFamily = FontFamily.Monospace
                )
            )

            Spacer(modifier = Modifier.height(20.dp))
            CustomInput(
                contentValue = email, valueChanged = onEmailChanged,
                params = INPUT_FIELD_PARAMS.EMAIL
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomInput(
                contentValue = password, valueChanged = onPwdChanged,
                params = INPUT_FIELD_PARAMS.PASSWORD
            )
            Spacer(modifier = Modifier.height(20.dp))

            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                CustomButton(
                    buttonText = "Login",
                    isButtonRounded = true,
                    cornersInDp = 50.dp,
                    buttonPaddingInDp = 20.dp
                ){
                    // Click action is received here
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

           Row(
               horizontalArrangement = Arrangement.Center,
               verticalAlignment = Alignment.CenterVertically
           ) {

               ClickableText(
                   text = AnnotatedString("Forgot password"),
                   onClick = {},
                   style = TextStyle(
                       fontSize = 14.sp,
                       fontFamily = FontFamily.Default
                   )
               )
           }

        }

    }
}




@Composable
@Preview(showBackground = true)
fun LoginPagePreview() { LoginPageContent("userName", "password",{},{}) }