package com.iprayforgod.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.compiler.plugins.kotlin.EmptyFunctionMetrics.composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.iprayforgod.core.navigation.Route
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iprayforgod.core.navigation.UiEvent.Navigate

import com.iprayforgod.core_ui.theme.AppTheme
import com.iprayforgod.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {

    private val viewModel: MainVm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.ONBOARDING
                ) {
                    composable(Route.ONBOARDING) {
                        WelcomeScreen(onNavigate = navController::navigate)
                    }
                }
            }
        }
    }
}