package com.iprayforgod.core.data.implementation.logger.utilities

import android.content.Context
import com.iprayforgod.core.data.implementation.logger.utilities.modules.OrHaNoButModule
import com.iprayforgod.core.data.implementation.logger.utilities.modules.TimberModule

class AppLoggerConfig(val context: Context?) {

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

    fun initializeLogging() {
        context?.let {
            OrHaNoButModule().initialize(it)
            TimberModule().initialize(it)
        }
    }
}
