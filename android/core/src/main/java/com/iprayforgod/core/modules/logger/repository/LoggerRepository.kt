package com.iprayforgod.core.modules.logger.repository

import com.iprayforgod.core.domain.LoggerFeature

class LoggerRepository(
    private val loggerFeature: LoggerFeature
) {
    fun d(msg: String?) { loggerFeature.d(msg) }
    fun e(msg: String?) { loggerFeature.e(msg) }
    fun w(msg: String?) { loggerFeature.w(msg) }
    fun v(msg: String?) { loggerFeature.v(msg) }
    fun i(msg: String?) { loggerFeature.i(msg) }
}
