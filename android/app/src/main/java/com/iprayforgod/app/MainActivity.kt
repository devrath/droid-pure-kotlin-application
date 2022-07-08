package com.iprayforgod.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.compiler.plugins.kotlin.EmptyFunctionMetrics.composable
import com.iprayforgod.core.navigation.Route
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iprayforgod.core_ui.theme.AppTheme
import com.iprayforgod.core_ui.theme.CaloryTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.ONBOARDING
                ) {
                    composable(Route.ONBOARDING) {
                        //WelcomeScreen(onNavigate = navController::navigate)
                    }

                }
            }
        }
    }
}