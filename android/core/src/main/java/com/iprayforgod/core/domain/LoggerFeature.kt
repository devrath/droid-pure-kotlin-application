package com.iprayforgod.core.domain

import com.iprayforgod.core.modules.logger.AppLoggerConfig
import timber.log.Timber

interface LoggerFeature {
    fun d(msg: String?)
    fun e(msg: String?)
    fun w(msg: String?)
    fun v(msg: String?)
    fun i(msg: String?)
}