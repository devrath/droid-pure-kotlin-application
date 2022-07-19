package com.iprayforgod.core.modules.logger.implementation

import com.iprayforgod.core.domain.LoggerFeature
import com.iprayforgod.core.modules.logger.AppLoggerConfig
import timber.log.Timber

class LoggerFeatureImpl() : LoggerFeature {
    override fun d(msg: String?) { msg?.let { Timber.tag(AppLoggerConfig.TAG_LOG_CAT).d(it) } }
    override fun e(msg: String?) { msg?.let { Timber.tag(AppLoggerConfig.TAG_LOG_CAT).e(it) } }
    override fun w(msg: String?) { msg?.let { Timber.tag(AppLoggerConfig.TAG_LOG_CAT).w(it) } }
    override fun v(msg: String?) { msg?.let { Timber.tag(AppLoggerConfig.TAG_LOG_CAT).v(it) } }
    override fun i(msg: String?) { msg?.let { Timber.tag(AppLoggerConfig.TAG_LOG_CAT).i(it) } }
}
