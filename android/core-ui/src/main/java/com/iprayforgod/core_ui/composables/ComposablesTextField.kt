package com.iprayforgod.core_ui.composables

import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

enum class InputFieldParams { EMAIL, PASSWORD, TEXT }

@Composable
fun CustomInput(
    label: String,
    contentValue: String,
    valueChanged: (String) -> Unit,
    params: InputFieldParams = InputFieldParams.TEXT,
    keyboardOnNext: (KeyboardActionScope) -> Unit,
    keyboardOnDone: (KeyboardActionScope) -> Unit,
    imeAction: ImeAction = ImeAction.Done
) {

    when (params) {
        InputFieldParams.EMAIL -> InputEmail(label, contentValue, valueChanged, keyboardOnNext, keyboardOnDone, imeAction)
        InputFieldParams.PASSWORD -> InputPassword(label, contentValue, valueChanged, keyboardOnNext, keyboardOnDone, imeAction)
        InputFieldParams.TEXT -> InputText(label, contentValue, valueChanged, keyboardOnNext, keyboardOnDone, imeAction)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun InputEmail(
    label: String,
    emailValue: String,
    valueChanged: (String) -> Unit,
    keyboardOnNext: (KeyboardActionScope) -> Unit,
    keyboardOnDone: (KeyboardActionScope) -> Unit,
    imeAction: ImeAction = ImeAction.Done
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        label = { Text(text = label) },
        value = emailValue,
        singleLine = true,
        onValueChange = valueChanged,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onNext = keyboardOnNext,
            onDone = keyboardOnDone
        )
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun InputPassword(
    label: String,
    passwordValue: String,
    valueChanged: (String) -> Unit,
    keyboardOnNext: (KeyboardActionScope) -> Unit,
    keyboardOnDone: (KeyboardActionScope) -> Unit,
    imeAction: ImeAction = ImeAction.Done
) {
   /* val keyboardController = LocalSoftwareKeyboardController.current
    keyboardController?.hide()*/
    TextField(
        label = { Text(text = label) },
        value = passwordValue,
        onValueChange = valueChanged,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onNext = keyboardOnNext,
            onDone = keyboardOnDone
        )
    )
}

@Composable
private fun InputText(
    label: String,
    textValue: String,
    valueChanged: (String) -> Unit,
    keyboardOnNext: (KeyboardActionScope) -> Unit,
    keyboardOnDone: (KeyboardActionScope) -> Unit,
    imeAction: ImeAction = ImeAction.Done
) {
    TextField(
        label = { Text(text = label) },
        value = textValue,
        onValueChange = valueChanged,
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onNext = keyboardOnNext,
            onDone = keyboardOnDone
        )
    )
}
