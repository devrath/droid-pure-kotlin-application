package com.iprayforgod.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.droid.login_presentation.view.LoginScreen
import com.iprayforgod.app.navigation.Route
import com.iprayforgod.core_ui.theme.AppTheme
import com.iprayforgod.view.OnBoardingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainVm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply { setKeepOnScreenCondition { viewModel.isLoading.value } }
        setContent { CurrentScreen() }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() { CurrentScreen() }

    @Composable
    private fun CurrentScreen() {
        AppTheme {
            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                backgroundColor = Color.Gray,
                scaffoldState = scaffoldState
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Route.LOGIN
                ){
                    //composable(Route.ON_BOARDING) { OnBoardingScreen() }
                    composable(Route.LOGIN) { LoginScreen() }
                }
            }
        }
    }
}