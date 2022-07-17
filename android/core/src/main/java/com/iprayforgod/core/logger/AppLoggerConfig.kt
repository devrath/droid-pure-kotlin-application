package com.iprayforgod.core.logger

import android.content.Context
import com.iprayforgod.core.logger.modules.OrHaNoButModule
import com.iprayforgod.core.logger.modules.TimberModule
import com.orhanobut.logger.*
import timber.log.Timber

class AppLoggerConfig {

    companion object {
        // used to search the logs in log-cat
        const val TAG_LOG_CAT = "DEBUG-LOGS"
        // Parent Directory Name
        const val LOG_DIRECTORY_PARENT_NAME = "android-native"
        // Child Directory Name
        const val LOG_DIRECTORY_NAME = "logs"
        /***********************************************/
        const val DISK_LOG_FILE_SIZE = 500 * 1024
        /***********************************************/
        const val CsvStrategyTag = "tag"
    }


    fun initializeLogging(context:Context) {
        OrHaNoButModule().initialize(context)
        TimberModule().initialize()
    }

}