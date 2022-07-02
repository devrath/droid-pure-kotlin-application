package com.iprayforgod.splash_presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.iprayforgod.splash_presentation.vm.SplashVm
import com.iprayforgod.view.OnBoardingActivity

class SplashActivity : ComponentActivity() {

    private val viewModel: SplashVm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                // As long as this value is true in view model the splash screen will keep loading
                viewModel.isLoading.value
            }
        }
        startActivity(Intent(this, OnBoardingActivity::class.java))

    }

}