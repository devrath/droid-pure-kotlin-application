package com.iprayforgod.core.modules.logger

import com.iprayforgod.core.modules.logger.AppLoggerConfig.Companion.TAG_LOG_CAT
import timber.log.Timber

/**
 * Android Log wrapper class that can use [String.format] in logging message
 */
object AppLogger {
    fun d(msg: String?) { msg?.let { Timber.tag(TAG_LOG_CAT).d(it) } }
    fun e(msg: String?) { msg?.let { Timber.tag(TAG_LOG_CAT).e(it) } }
    fun w(msg: String?) { msg?.let { Timber.tag(TAG_LOG_CAT).w(it) } }
    fun v(msg: String?) { msg?.let { Timber.tag(TAG_LOG_CAT).v(it) } }
    fun i(msg: String?) { msg?.let { Timber.tag(TAG_LOG_CAT).i(it) } }
}
