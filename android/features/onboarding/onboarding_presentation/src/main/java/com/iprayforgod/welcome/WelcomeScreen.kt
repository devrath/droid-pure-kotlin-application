package com.iprayforgod.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.com.iprayforgod.onboarding_presentation.R
import com.iprayforgod.components.ActionButton
import com.iprayforgod.core.navigation.Route
import com.iprayforgod.core.navigation.UiEvent
import com.iprayforgod.core_ui.theme.LocalSpacing

@Composable
fun WelcomeScreen(
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.str_welcome_txt),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        ActionButton(
            text = stringResource(id = R.string.str_next),
            onClick = {
                        //onNavigate(UiEvent.Navigate(Route.AGE))
                      },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}