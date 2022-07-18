package com.iprayforgod.core.modules.logger.adapters

import android.content.Context
import com.iprayforgod.core.modules.logger.AppLoggerConfig
import com.iprayforgod.core.modules.logger.utils.DiskLogHandler
import com.orhanobut.logger.BuildConfig
import com.orhanobut.logger.CsvFormatStrategy
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.DiskLogStrategy
import java.io.File

class AndroidDiskLogAdapter {


    fun initiate(context: Context): DiskLogAdapter {
        // Location where the log file is created
        val file = File(context.getExternalFilesDir(AppLoggerConfig.LOG_DIRECTORY_PARENT_NAME),
            AppLoggerConfig.LOG_DIRECTORY_NAME
        )
        if (!file.exists()) { file.mkdir() }

        return setUp(file)
    }


    private fun setUp(file: File) = object : DiskLogAdapter(
        CsvFormatStrategy.newBuilder()
            .tag(AppLoggerConfig.CsvStrategyTag)
            .logStrategy(DiskLogStrategy(DiskLogHandler(
                file.absolutePath, BuildConfig.APPLICATION_ID, AppLoggerConfig.DISK_LOG_FILE_SIZE))
            ).build()
    ) {
        override fun isLoggable(priority: Int, tag: String?): Boolean {
            return true;
        }
    }

}