package com.iprayforgod.core.logger.modules

import com.orhanobut.logger.Logger
import timber.log.Timber

class TimberModule {

    fun initialize() {
        Timber.plant(timberModule())
    }

    private fun timberModule() = object : Timber.DebugTree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            Logger.log(priority, tag, message, t)
        }
    }
}