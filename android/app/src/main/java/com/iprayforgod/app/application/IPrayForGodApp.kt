package com.iprayforgod.app.application

import android.app.Application
import com.iprayforgod.app.BuildConfig
import com.iprayforgod.core.modules.logger.AppLoggerConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class IPrayForGodApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            AppLoggerConfig(this).initializeLogging()
        }
    }
}
