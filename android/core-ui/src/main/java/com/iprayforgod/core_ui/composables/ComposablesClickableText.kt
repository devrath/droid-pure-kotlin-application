package com.iprayforgod.core_ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomClickableText(
    contentValue:String,
    textColor: Color,
    modifier: Modifier = Modifier.padding(0.dp),
    fontSize: TextUnit = 9.sp,
    onClick: (Int) -> Unit
){
    ClickableText(
        text = AnnotatedString(contentValue),
        onClick = onClick,
        modifier = modifier,
        style = TextStyle(
            fontSize = fontSize,
            color= textColor,
            fontFamily = FontFamily.Default
        )
    )
}