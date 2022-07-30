package com.iprayforgod.core.modules.logger.repository

import com.iprayforgod.core.domain.features.logger.LoggerFeature

class LoggerRepository(
    private val loggerFeature: LoggerFeature
) {
    fun d(featureName: String?, msg: String?) { loggerFeature.d(featureName,msg) }
    fun e(featureName: String?, msg: String?) { loggerFeature.e(featureName,msg) }
    fun w(featureName: String?, msg: String?) { loggerFeature.w(featureName,msg) }
    fun v(featureName: String?, msg: String?) { loggerFeature.v(featureName,msg) }
    fun i(featureName: String?, msg: String?) { loggerFeature.i(featureName,msg) }
}
