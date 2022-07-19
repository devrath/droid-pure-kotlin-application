package com.iprayforgod.core_ui.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    contentValue: String,
    textColor: Color,
    fontSize: TextUnit = 9.sp
) {
    Text(
        text = contentValue,
        color = textColor,
        style = TextStyle(
            fontSize = fontSize,
            fontFamily = FontFamily.Monospace
        )
    )
}
