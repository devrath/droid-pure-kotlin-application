package com.iprayforgod.core.data.implementation.logger.utilities.modules

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.iprayforgod.core.domain.models.DeviceDetails
import com.orhanobut.logger.Logger
import timber.log.Timber

class TimberModule {

    @SuppressLint("HardwareIds")
    fun initialize(context: Context) {
        val deviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        val deviceDetails = DeviceDetails(deviceId)
        val remoteTree = TimberRemoteTree(deviceDetails)
        Timber.plant(remoteTree)
    }

    private fun timberModule() = object : Timber.DebugTree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            Logger.log(priority, tag, message, t)
        }
    }
}
