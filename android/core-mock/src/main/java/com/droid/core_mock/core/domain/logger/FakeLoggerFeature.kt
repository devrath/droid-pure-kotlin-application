package com.droid.core_mock.core.domain.logger

import com.iprayforgod.core.domain.features.logger.LoggerFeature

class FakeLoggerFeature : LoggerFeature {
    override fun d(featureName: String?, msg: String?) {}
    override fun e(featureName: String?, msg: String?) {}
    override fun w(featureName: String?, msg: String?) {}
    override fun v(featureName: String?, msg: String?) {}
    override fun i(featureName: String?, msg: String?) {}
}
