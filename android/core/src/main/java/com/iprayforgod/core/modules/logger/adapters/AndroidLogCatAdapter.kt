package com.iprayforgod.core.modules.logger.adapters

import com.iprayforgod.core.modules.logger.AppLoggerConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.PrettyFormatStrategy

class AndroidLogCatAdapter {

    fun initiate(): AndroidLogAdapter {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
            .methodCount(2) // (Optional) How many method line to show. Default 2
            .methodOffset(5) // (Optional) Skips some method invokes in stack trace. Default 5
            // .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
            .tag(AppLoggerConfig.TAG_LOG_CAT) // (Optional) Custom tag for each log. Default PRETTY_LOGGER
            .build()
        return AndroidLogAdapter(formatStrategy)
    }
}
