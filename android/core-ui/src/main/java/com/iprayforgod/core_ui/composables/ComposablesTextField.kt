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

enum class InputFieldParams { EMAIL, PASSWORD, TEXT }

@Composable
fun CustomInput(
    label: String,
    contentValue: String,
    valueChanged: (String) -> Unit,
    params: InputFieldParams = InputFieldParams.TEXT
) {

    when (params) {
        InputFieldParams.EMAIL -> InputEmail(label, contentValue, valueChanged)
        InputFieldParams.PASSWORD -> InputPassword(label, contentValue, valueChanged)
        InputFieldParams.TEXT -> InputText(label, contentValue, valueChanged)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun InputEmail(
    label: String,
    emailValue: String,
    valueChanged: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        label = { Text(text = label) },
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
    label: String,
    passwordValue: String,
    valueChanged: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        label = { Text(text = label) },
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
    label: String,
    textValue: String,
    valueChanged: (String) -> Unit
) {
    TextField(
        label = { Text(text = label) },
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
