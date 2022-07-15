package com.iprayforgod.core_ui.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

enum class INPUT_FIELD_PARAMS { EMAIL, PASSWORD, TEXT}

@Composable
fun CustomInput(
    contentValue:String,
    valueChanged:(String) -> Unit,
    params:INPUT_FIELD_PARAMS = INPUT_FIELD_PARAMS.TEXT
){

    when(params){
        INPUT_FIELD_PARAMS.EMAIL -> InputEmail(contentValue,valueChanged)
        INPUT_FIELD_PARAMS.PASSWORD -> InputPassword(contentValue,valueChanged)
        INPUT_FIELD_PARAMS.TEXT -> InputText(contentValue,valueChanged)
    }

}

@Composable
private fun InputEmail(
    emailValue: String,
    valueChanged: (String) -> Unit,
) {
    TextField(
        label = { Text(text = "Email") },
        value = emailValue,
        singleLine = true,
        onValueChange = valueChanged,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}


@Composable
private fun InputPassword(
    passwordValue:String,
    valueChanged:(String) -> Unit
) {
    TextField(
        label = { Text(text = "Password") },
        value = passwordValue,
        onValueChange = valueChanged,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
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