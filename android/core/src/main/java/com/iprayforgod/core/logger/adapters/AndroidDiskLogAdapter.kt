package com.iprayforgod.core.logger.adapters

import android.content.Context
import com.iprayforgod.core.logger.AppLogger
import com.iprayforgod.core.logger.utils.DiskLogHandler
import com.orhanobut.logger.BuildConfig
import com.orhanobut.logger.CsvFormatStrategy
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.DiskLogStrategy
import java.io.File

class AndroidDiskLogAdapter {


    fun initiate(context: Context): DiskLogAdapter {
        // Location where the log file is created
        val file = File(context.getExternalFilesDir(AppLogger.LOG_DIRECTORY_PARENT_NAME),
            AppLogger.LOG_DIRECTORY_NAME
        )
        if (!file.exists()) { file.mkdir() }

        return setUp(file)
    }


    private fun setUp(file: File) = object : DiskLogAdapter(
        CsvFormatStrategy.newBuilder()
            .tag(AppLogger.CsvStrategyTag)
            .logStrategy(DiskLogStrategy(DiskLogHandler(
                file.absolutePath, BuildConfig.APPLICATION_ID, AppLogger.DISK_LOG_FILE_SIZE))
            ).build()
    ) {
        override fun isLoggable(priority: Int, tag: String?): Boolean {
            return true;
        }
    }

}