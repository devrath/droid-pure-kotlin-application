package com.iprayforgod.splash_presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.iprayforgod.core_ui.theme.AppTheme
import com.iprayforgod.splash_presentation.vm.SplashVm

class SplashActivity : ComponentActivity() {

    private val viewModel: SplashVm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepVisibleCondition {
                // As long as this value is true in view model the splash screen will keep loading
                viewModel.isLoading.value
            }
        }
        setContent {
            AppTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Hello World!")
                }
            }
        }
    }

    private fun setKeepVisibleCondition(function: () -> Boolean) {

    }
}