package com.iprayforgod.core_ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    buttonText: String,
    isButtonRounded: Boolean = false,
    cornersInDp: Dp = 0.dp,
    buttonPaddingInDp: Dp = 0.dp,
    onClickAction: () -> Unit
) {
    Button(
        // Click action
        onClick = onClickAction,
        // Modifier properties
        modifier = Modifier.fillMaxWidth().padding(buttonPaddingInDp),
        // Shape of the button
        shape = setButtonShape(isRounded = isButtonRounded, roundedCornersInDp = cornersInDp)
    ) {
        SetText(buttonText)
    }
}

/**
 * Setting the shape of composable
 * @param isRounded
 * @param roundedCornersInDp
 * @return ---> Shape of button
 */
@Composable
private fun setButtonShape(
    isRounded: Boolean,
    roundedCornersInDp: Dp
) = when {
    isRounded -> { RoundedCornerShape(roundedCornersInDp) }
    else -> MaterialTheme.shapes.small
}

/**
 * Sets the text composable
 * @param buttonText -> Text to be set
 * @return text composable to be used
 */
@Composable
private fun SetText(buttonText: String) {
    Text(
        text = buttonText, textAlign = TextAlign.Center
    )
}
