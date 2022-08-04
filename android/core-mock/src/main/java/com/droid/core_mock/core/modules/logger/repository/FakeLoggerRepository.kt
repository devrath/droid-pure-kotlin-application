package com.droid.core_mock.core.modules.logger.repository

import com.droid.core_mock.core.domain.logger.FakeLoggerFeature
import com.iprayforgod.core.modules.logger.repository.LoggerRepository

class FakeLoggerRepository : LoggerRepository(loggerFeature = FakeLoggerFeature()) {
    override fun d(featureName: String?, msg: String?) { }
    override fun e(featureName: String?, msg: String?) { }
    override fun w(featureName: String?, msg: String?) { }
    override fun v(featureName: String?, msg: String?) { }
    override fun i(featureName: String?, msg: String?) { }
}
