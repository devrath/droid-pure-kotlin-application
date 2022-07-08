package com.iprayforgod.app

import androidx.navigation.NavController
import com.iprayforgod.core.navigation.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}