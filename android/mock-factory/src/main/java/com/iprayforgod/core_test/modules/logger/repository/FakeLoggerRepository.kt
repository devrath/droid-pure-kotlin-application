package com.iprayforgod.core_test.modules.logger.repository

import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.core_test.domain.logger.FakeLoggerFeature

class FakeLoggerRepository : LoggerRepository(loggerFeature = FakeLoggerFeature()) {
    override fun d(featureName: String?, msg: String?) { }
    override fun e(featureName: String?, msg: String?) { }
    override fun w(featureName: String?, msg: String?) { }
    override fun v(featureName: String?, msg: String?) { }
    override fun i(featureName: String?, msg: String?) { }
}