package com.iprayforgod.core_ui.composables

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

enum class InputFieldParams { EMAIL, PASSWORD, TEXT}

@Composable
fun CustomInput(
    contentValue:String,
    valueChanged:(String) -> Unit,
    params:InputFieldParams = InputFieldParams.TEXT
){

    when(params){
        InputFieldParams.EMAIL -> InputEmail(contentValue,valueChanged)
        InputFieldParams.PASSWORD -> InputPassword(contentValue,valueChanged)
        InputFieldParams.TEXT -> InputText(contentValue,valueChanged)
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun InputEmail(
    emailValue: String,
    valueChanged: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        label = { Text(text = "Email") },
        value = emailValue,
        singleLine = true,
        onValueChange = valueChanged,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        keyboardActions = HideKeyBoard(keyboardController)
    )
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun InputPassword(
    passwordValue:String,
    valueChanged:(String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        label = { Text(text = "Password") },
        value = passwordValue,
        onValueChange = valueChanged,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = HideKeyBoard(keyboardController)
    )
}

@Composable
private fun InputText(
    textValue:String,
    valueChanged:(String) -> Unit
) {
    TextField(
        label = { Text(text = "Password") },
        value = textValue,
        onValueChange = valueChanged,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}




/** ************************** Utils **************************************** **/

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun HideKeyBoard(keyboardController: SoftwareKeyboardController?) =
    KeyboardActions(onDone = { keyboardController?.hide() })
/** ************************** Utils **************************************** **/
